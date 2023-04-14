package com.wrsungwebflux.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                    .anyExchange().authenticated()
                    .and()

                .httpBasic();

        return http.build();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        String encodedPassword = passwordEncoder().encode("123!@#");

        UserDetails userDetails = User.withUsername("iotree")
                .password(encodedPassword)
                .roles("USER")
                .build();

        return new MapReactiveUserDetailsService(userDetails);
    }

}
