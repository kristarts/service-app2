package com.softserve.itacademy.vkhomenko.serviceapp2.service;

import com.softserve.itacademy.vkhomenko.serviceapp2.dto.user.UserDto;
import com.softserve.itacademy.vkhomenko.serviceapp2.dto.user.UserMapper;
import com.softserve.itacademy.vkhomenko.serviceapp2.entity.user.UserEntity;
import com.softserve.itacademy.vkhomenko.serviceapp2.repository.UserRepository;
import com.softserve.itacademy.vkhomenko.serviceapp2.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not exist."));

        return UserSecurity.createFromEntity(userEntity);
    }

    public List<UserDto> getAllUsers() {

        List<UserEntity> userEntityList = userRepository.findAll();

        List<UserDto> userDtoList = userEntityList.stream()
                .map(UserMapper.INSTANCE::userEntityToUserDto)
                .collect(Collectors.toList());

        return userDtoList;
    }

}
