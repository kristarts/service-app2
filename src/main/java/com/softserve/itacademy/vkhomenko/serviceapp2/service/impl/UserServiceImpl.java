package com.softserve.itacademy.vkhomenko.serviceapp2.service.impl;

import com.softserve.itacademy.vkhomenko.serviceapp2.entity.UserEntity;
import com.softserve.itacademy.vkhomenko.serviceapp2.repository.UserRepository;
import com.softserve.itacademy.vkhomenko.serviceapp2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        return userRepository.saveAndFlush(userEntity);
    }

    @Override
    public void delete(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }

    @Override
    public UserEntity findByName(String name) {
        //return userRepository.findByName(name);
        return null;
    }

    @Override
    public UserEntity editUserEntity(UserEntity userEntity) {
        return userRepository.saveAndFlush(userEntity);
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

}
