package com.softserve.itacademy.vkhomenko.serviceapp2.security.auth;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String email;
    private String password;
}
