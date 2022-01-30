package com.softserve.itacademy.vkhomenko.serviceapp2.service;

import com.softserve.itacademy.vkhomenko.serviceapp2.entity.UserEntity;
import com.softserve.itacademy.vkhomenko.serviceapp2.repository.UserRepository;
import com.softserve.itacademy.vkhomenko.serviceapp2.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not exist"));

        return UserSecurity.createFromEntity(userEntity);
    }

    public UserEntity addUser(UserEntity userEntity) {
        return userRepository.saveAndFlush(userEntity);
    }

    public void delete(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }

    public UserEntity editUserEntity(UserEntity userEntity) {
        return userRepository.saveAndFlush(userEntity);
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

}
