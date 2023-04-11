package com.wrsungwebflux.service;

import com.wrsungwebflux.exception.NoSuchDataException;
import com.wrsungwebflux.repository.UserRepository;
import com.wrsungwebflux.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<List<UserVo>> getUserList() {
        return userRepository.findAll().collectList();
    }

    public Mono<UserVo> getUserById(Long id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new NoSuchDataException("No such data exists.")));
    }

    @Transactional
    public Mono<UserVo> createUser(UserVo userVo) {
        return userRepository.save(userVo)
                .flatMap(userVo1 -> Mono.error(new RuntimeException("@Transactional test.")));
    }

}
