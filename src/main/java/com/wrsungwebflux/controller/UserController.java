package com.wrsungwebflux.controller;

import com.wrsungwebflux.consts.ResCode;
import com.wrsungwebflux.dto.GetUserListRespDto;
import com.wrsungwebflux.dto.GetUserRespDto;
import com.wrsungwebflux.exception.NoSuchDataException;
import com.wrsungwebflux.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Mono<GetUserListRespDto> getUserList() {
        return userService.getUserList()
                .map(userList -> {
                    GetUserListRespDto getUserListRespDto = new GetUserListRespDto();
                    getUserListRespDto.setUserList(userList);
                    return getUserListRespDto;
                })
                .onErrorResume(e -> Mono.just(new GetUserListRespDto(null, ResCode.UNKNOWN.value(), e.getLocalizedMessage())));
    }

    @GetMapping("/users/{id}")
    public Mono<GetUserRespDto> getUser(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(userVo -> {
                    GetUserRespDto getUserRespDto = new GetUserRespDto();
                    getUserRespDto.setUser(userVo);
                    return getUserRespDto;
                })
                .onErrorResume(NoSuchDataException.class, e -> Mono.just(new GetUserRespDto(null, ResCode.NO_SUCH_DATA.value(), "No such user exists.")))
                .onErrorResume(e -> Mono.just(new GetUserRespDto(null, ResCode.UNKNOWN.value(), e.getLocalizedMessage())));
    }

}
