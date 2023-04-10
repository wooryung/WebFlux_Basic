package com.wrsungwebflux.service;

import com.wrsungwebflux.exception.NoSuchDataException;
import com.wrsungwebflux.vo.PostVo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PostService {

    private final WebClient webClient = WebClient.builder().build();

    public Mono<List<PostVo>> getPostList() {
        return webClient.get()
                .uri("https://jsonplaceholder.typicode.com/posts")
                .retrieve()
                .bodyToFlux(PostVo.class)
                .collectList();
    }

    public Mono<PostVo> getPostById(Long id) {
        return webClient.get()
                .uri("https://jsonplaceholder.typicode.com/posts/{id}", id)
                .retrieve()
                .bodyToMono(PostVo.class)
                .switchIfEmpty(Mono.error(new NoSuchDataException("No such data exists.")));
    }
}
