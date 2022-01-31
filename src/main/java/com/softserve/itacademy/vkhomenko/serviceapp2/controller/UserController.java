package com.softserve.itacademy.vkhomenko.serviceapp2.controller;

import com.softserve.itacademy.vkhomenko.serviceapp2.service.ArticleService;
import com.softserve.itacademy.vkhomenko.serviceapp2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final ArticleService articleService;

    @Autowired
    public UserController(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    @GetMapping("/{userId}/article")
    public ResponseEntity<?> getAllArticles(@PathVariable String userId,
                                            @RequestParam int page,
                                            @RequestParam int size) {
        return ResponseEntity.ok(articleService.getAllArticlesFromUser(userId, page, size));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
