package com.wrsungwebflux.controller;

import com.wrsungwebflux.consts.ResCode;
import com.wrsungwebflux.dto.user.CreateUserReqDto;
import com.wrsungwebflux.dto.user.CreateUserRespDto;
import com.wrsungwebflux.dto.user.GetUserListRespDto;
import com.wrsungwebflux.dto.user.GetUserRespDto;
import com.wrsungwebflux.exception.NoSuchDataException;
import com.wrsungwebflux.service.UserService;
import com.wrsungwebflux.vo.UserVo;
import io.r2dbc.spi.R2dbcDataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
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

    @PostMapping("/users")
    public Mono<CreateUserRespDto> createUser(@RequestBody CreateUserReqDto createUserReqDto) {
        UserVo userVo = new UserVo(createUserReqDto.getName(), createUserReqDto.getUsername(), createUserReqDto.getEmail(), createUserReqDto.getPassword(), createUserReqDto.getAddress(), createUserReqDto.getPhone(), createUserReqDto.getWebsite(), createUserReqDto.getCompany());
        return userService.createUser(userVo)
                .map(userVo1 -> new CreateUserRespDto())
                .onErrorResume(RuntimeException.class,
                        e -> {
                            if (e.getCause() instanceof R2dbcDataIntegrityViolationException)
                                return  Mono.just(new CreateUserRespDto(ResCode.DUPLICATE_KEY.value(), "This 'username' or 'email' already exists."));
                            else
                                return Mono.just(new CreateUserRespDto(-3, "Failed to insert user."));
                        }
                )
                .onErrorResume(e -> Mono.just(new CreateUserRespDto(ResCode.UNKNOWN.value(), e.getLocalizedMessage())));

    }

}
