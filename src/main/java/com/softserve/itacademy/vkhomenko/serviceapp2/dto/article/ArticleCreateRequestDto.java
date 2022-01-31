package com.softserve.itacademy.vkhomenko.serviceapp2.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleCreateRequestDto {
    @NotNull(message = "Article name is not filled.")
    @Size(min = 3, max = 30, message = "Article name length 3-30 symbols.")
    private String articleName;

    @NotNull(message = "Article text is not filled.")
    @Size(min = 10, max = 200, message = "Article text length 10-200 symbols.")
    private String articleText;
}
