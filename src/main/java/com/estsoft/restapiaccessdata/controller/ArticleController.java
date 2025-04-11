package com.estsoft.restapiaccessdata.controller;

import com.estsoft.restapiaccessdata.domain.Article;
import com.estsoft.restapiaccessdata.dto.ArticleResponseDto;
import com.estsoft.restapiaccessdata.service.ArticleService;
import com.estsoft.restapiaccessdata.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
    }

    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<ArticleResponseDto> getArticleById(@PathVariable Long articleId) {
        Article article = articleService.getArticleById(articleId);

        return ResponseEntity.ok(new ArticleResponseDto(article));
    }
}
