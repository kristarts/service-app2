package com.softserve.itacademy.vkhomenko.serviceapp2.repository;

import com.softserve.itacademy.vkhomenko.serviceapp2.entity.article.ArticleEntity;
import com.softserve.itacademy.vkhomenko.serviceapp2.entity.user.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {

    List<ArticleEntity> findAllByUserEntity(UserEntity userEntity, Pageable pageable);

}
