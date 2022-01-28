package com.softserve.itacademy.vkhomenko.serviceapp2.config;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String email;
    private String password;
}
