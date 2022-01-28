package com.softserve.itacademy.vkhomenko.serviceapp2.service;

import com.softserve.itacademy.vkhomenko.serviceapp2.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity addUser(UserEntity userEntity);
    void delete(UserEntity userEntity);
    UserEntity findByName(String name);
    UserEntity editUserEntity(UserEntity userEntity);
    List<UserEntity> getAll();
}
