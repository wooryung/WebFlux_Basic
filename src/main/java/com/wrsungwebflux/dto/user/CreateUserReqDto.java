package com.wrsungwebflux.dto.user;

import lombok.Data;

@Data
public class CreateUserReqDto {
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String website;
    private String company;
}
