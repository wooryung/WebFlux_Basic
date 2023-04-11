package com.wrsungwebflux.controller;

import com.wrsungwebflux.consts.ResCode;
import com.wrsungwebflux.dto.post.GetPostListRespDto;
import com.wrsungwebflux.dto.post.GetPostRespDto;
import com.wrsungwebflux.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

import java.net.UnknownHostException;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public Mono<GetPostListRespDto> getPostList() {
        return postService.getPostList()
                .map(postList -> {
                    GetPostListRespDto getPostListRespDto = new GetPostListRespDto();
                    getPostListRespDto.setPostList(postList);
                    return getPostListRespDto;
                })
                .onErrorResume(WebClientRequestException.class,
                        e -> {
                            if (e.getCause() instanceof UnknownHostException)
                                return Mono.just(new GetPostListRespDto(null, -4, e.getLocalizedMessage()));
                            else
                                return Mono.just(new GetPostListRespDto(null, -90, e.getLocalizedMessage()));
                        }
                )
                .onErrorResume(e -> Mono.just(new GetPostListRespDto(null, ResCode.UNKNOWN.value(), e.getLocalizedMessage())));
    }

    @GetMapping("/posts/{id}")
    public Mono<GetPostRespDto> getPost(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(postVo -> {
                    GetPostRespDto getPostRespDto = new GetPostRespDto();
                    getPostRespDto.setPost(postVo);
                    return getPostRespDto;
                })
                .onErrorResume(WebClientRequestException.class,
                        e -> {
                            if (e.getCause() instanceof UnknownHostException)
                                return Mono.just(new GetPostRespDto(null, -4, e.getLocalizedMessage()));
                            else
                                return Mono.just(new GetPostRespDto(null, -90, e.getLocalizedMessage()));
                        }
                )
                .onErrorResume(e -> Mono.just(new GetPostRespDto(null, ResCode.UNKNOWN.value(), e.getLocalizedMessage())));
    }
}
