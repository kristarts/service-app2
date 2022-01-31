package com.softserve.itacademy.vkhomenko.serviceapp2.security.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {

    @NotNull(message = "Email is not filled.")
    @Size(min = 6, max = 20, message = "Email length 6-20 symbols.")
    @Pattern(regexp = "^\\w[a-zA-Z0-9.!#$%&\u2019*+/=?^_`{|}~\"-]{0,34}@((\\[?[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}]?)|(([a-zA-Z0-9][a-zA-Z\\-0-9]*\\.)+[a-zA-Z]+))$",
            message = "Email is invalid.")
    private String email;

    @NotNull(message = "Password is not filled.")
    @Size(min = 8, max = 20, message = "Password length 8-20 symbols.")
    private String password;
}
