package com.softserve.itacademy.vkhomenko.serviceapp2.service;

import com.softserve.itacademy.vkhomenko.serviceapp2.dto.article.ArticleCreateRequestDto;
import com.softserve.itacademy.vkhomenko.serviceapp2.dto.article.ArticleDto;
import com.softserve.itacademy.vkhomenko.serviceapp2.dto.article.ArticleMapper;
import com.softserve.itacademy.vkhomenko.serviceapp2.entity.article.ArticleEntity;
import com.softserve.itacademy.vkhomenko.serviceapp2.entity.user.UserEntity;
import com.softserve.itacademy.vkhomenko.serviceapp2.exception.ActionNotAllowedException;
import com.softserve.itacademy.vkhomenko.serviceapp2.exception.NotFoundException;
import com.softserve.itacademy.vkhomenko.serviceapp2.repository.ArticleRepository;
import com.softserve.itacademy.vkhomenko.serviceapp2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Validated
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public List<ArticleDto> getAllArticlesFromUser(String userId, int page, int size) {

        UserEntity userEntity = UserEntity.builder()
                .id(UuidValidator.validAndGetUuid(userId))
                .build();

        List<ArticleEntity> articleEntityList = articleRepository.findAllByUserEntity(
                userEntity,
                PageRequest.of(page, size,
                        Sort.by("name")
                                .ascending())
        );

        if(articleEntityList.isEmpty()) {
            throw new NotFoundException("Articles not found.");
        }

        List<ArticleDto> articleDtoList = articleEntityList.stream()
                .map(ArticleMapper.INSTANCE::articleEntityToArticleDto).collect(Collectors.toList());

        return articleDtoList;
    }

    public UUID createArticle(UUID userId, ArticleCreateRequestDto articleCreateRequestDto) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found."));

        ArticleEntity articleEntity = ArticleEntity.builder()
                .userEntity(userEntity)
                .name(articleCreateRequestDto.getArticleName())
                .text(articleCreateRequestDto.getArticleText())
                .build();

        return articleRepository.save(articleEntity).getId();
    }

    public ArticleDto getArticleById(String articleId) {
        ArticleEntity articleEntity = articleRepository.findById(
                        UuidValidator.validAndGetUuid(articleId)
                )
                .orElseThrow(() -> new NotFoundException("Article not found."));

        return ArticleMapper.INSTANCE.articleEntityToArticleDto(articleEntity);
    }

    public void deleteArticleById(UUID userId, String articleId) {
        UUID articleUuid = UuidValidator.validAndGetUuid(articleId);
        ArticleEntity articleEntity = articleRepository.findById(articleUuid)
                .orElseThrow(() -> new NotFoundException("Article not found."));

        if (articleEntity.getUserEntity().getId().equals(userId)) {
            articleRepository.deleteById(articleUuid);
        } else {
            throw new ActionNotAllowedException("Article belongs to another user.");
        }
    }
}
