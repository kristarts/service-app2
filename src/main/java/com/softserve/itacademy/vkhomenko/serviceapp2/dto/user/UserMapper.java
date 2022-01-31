package com.softserve.itacademy.vkhomenko.serviceapp2.dto.user;

import com.softserve.itacademy.vkhomenko.serviceapp2.entity.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", expression = "java(userEntity.getId().toString())")
    UserDto userEntityToUserDto(UserEntity userEntity);
}
