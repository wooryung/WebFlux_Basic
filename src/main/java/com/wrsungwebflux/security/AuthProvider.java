package com.wrsungwebflux.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class AuthProvider implements ReactiveAuthenticationManager {

    private final Map<String, AuthUserVo> users = new HashMap<>();

    @PostConstruct
    public void postConstruct() { // In-Memory user 정보 설정
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        users.put("iotree1", new AuthUserVo("iotree1", passwordEncoder().encode("123!@#"), authorities));
        users.put("iotree2", new AuthUserVo("iotree2", passwordEncoder().encode("123!@#"), authorities));
        users.put("iotree3", new AuthUserVo("iotree3", passwordEncoder().encode("123!@#"), authorities));
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        return findByUsername(username)
                .filter(authUserVo -> passwordEncoder().matches(password, authUserVo.getPassword()))
                .map(authUserVo -> (Authentication) new UsernamePasswordAuthenticationToken(username, null, authUserVo.getAuthorities()) )
                .switchIfEmpty(Mono.error(new BadCredentialsException("Invalid username or password.")));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private Mono<AuthUserVo> findByUsername(String username) {
        return Mono.justOrEmpty(users.get(username));
    }

}
