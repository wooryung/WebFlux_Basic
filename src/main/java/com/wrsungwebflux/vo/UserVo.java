package com.wrsungwebflux.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("User")
@RequiredArgsConstructor
public class UserVo {
    @Id
    private Long id;
    private final String name;
    private final String username;
    private final String email;
    private final String password;
    private final String address;
    private final String phone;
    private final String website;
    private final String company;
}
