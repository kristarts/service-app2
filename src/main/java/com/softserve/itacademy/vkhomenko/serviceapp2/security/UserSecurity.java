package com.softserve.itacademy.vkhomenko.serviceapp2.security;

import com.softserve.itacademy.vkhomenko.serviceapp2.entity.UserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Data
public class UserSecurity implements UserDetails {

    private UUID id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserSecurity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static UserDetails createFromEntity(UserEntity userEntity) {
        return new UserSecurity(
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }
}
