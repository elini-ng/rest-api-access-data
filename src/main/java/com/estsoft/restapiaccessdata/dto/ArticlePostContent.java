package com.estsoft.restapiaccessdata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePostContent {
    private int userId;
    private int id;
    private String title;
    private String body;
}
