package com.softserve.itacademy.vkhomenko.serviceapp2.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private String articleId;
    private String articleName;
    private String articleText;
    private String userName;
}
