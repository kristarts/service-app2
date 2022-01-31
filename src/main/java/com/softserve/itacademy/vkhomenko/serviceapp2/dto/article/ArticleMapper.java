package com.softserve.itacademy.vkhomenko.serviceapp2.dto.article;

import com.softserve.itacademy.vkhomenko.serviceapp2.entity.article.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mapping(target = "articleId", expression = "java(articleEntity.getId().toString())")
    @Mapping(source = "name", target = "articleName")
    @Mapping(source = "text", target = "articleText")
    @Mapping(target = "userName", expression = "java(articleEntity.getUserEntity() != null ? articleEntity.getUserEntity().getName() : \"\")")
    ArticleDto articleEntityToArticleDto(ArticleEntity articleEntity);
}
