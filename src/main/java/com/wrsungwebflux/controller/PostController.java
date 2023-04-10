package com.wrsungwebflux.controller;

import com.wrsungwebflux.consts.ResCode;
import com.wrsungwebflux.dto.GetPostListRespDto;
import com.wrsungwebflux.dto.GetPostRespDto;
import com.wrsungwebflux.exception.NoSuchDataException;
import com.wrsungwebflux.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
                .onErrorResume(NoSuchDataException.class, e -> Mono.just(new GetPostRespDto(null, ResCode.NO_SUCH_DATA.value(), "No such post exists.")))
                .onErrorResume(e -> Mono.just(new GetPostRespDto(null, ResCode.UNKNOWN.value(), e.getLocalizedMessage())));
    }
}
