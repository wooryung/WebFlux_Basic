package com.wrsungwebflux.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@RequiredArgsConstructor
public class AuthUserVo {
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;
}
