package com.softserve.itacademy.vkhomenko.serviceapp2.repository;

import com.softserve.itacademy.vkhomenko.serviceapp2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    //@Query("select u from users u where u.name = :name")
    //UserEntity findByName(@Param("name") String name);

    Optional<UserEntity> findByEmail(String email);
}
