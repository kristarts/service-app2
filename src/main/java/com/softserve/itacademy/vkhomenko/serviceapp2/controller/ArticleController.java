package com.softserve.itacademy.vkhomenko.serviceapp2.controller;

import com.softserve.itacademy.vkhomenko.serviceapp2.dto.article.ArticleCreateRequestDto;
import com.softserve.itacademy.vkhomenko.serviceapp2.entity.user.CurrentUser;
import com.softserve.itacademy.vkhomenko.serviceapp2.security.UserSecurity;
import com.softserve.itacademy.vkhomenko.serviceapp2.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Validated
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/{articleId}")
    public ResponseEntity<?> getArticleById(@PathVariable String articleId) {
        return ResponseEntity.ok(articleService.getArticleById(articleId));
    }

    @PostMapping("")
    public ResponseEntity<?> createArticle(@CurrentUser UserSecurity userSecurity,
                                           @Valid @RequestBody ArticleCreateRequestDto articleCreateRequestDto) {
        return ResponseEntity
                .created(URI.create(""))
                .body(articleService.createArticle(userSecurity.getId(), articleCreateRequestDto));
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> deleteArticleById(@CurrentUser UserSecurity userSecurity,
                                               @PathVariable String articleId) {
        articleService.deleteArticleById(userSecurity.getId(), articleId);
        return ResponseEntity.ok("");
    }
}
