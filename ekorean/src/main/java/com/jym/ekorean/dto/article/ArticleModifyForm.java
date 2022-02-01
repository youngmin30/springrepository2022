package com.jym.ekorean.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleModifyForm{

    private String title;

    private String body;

}