package com.wrsungwebflux.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("User")
public class UserVo {
    @Id
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String website;
    private String company;

    public UserVo(String name, String username, String email, String password, String address, String phone, String website, String company) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }
}
