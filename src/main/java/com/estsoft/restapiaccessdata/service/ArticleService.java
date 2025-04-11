package com.estsoft.restapiaccessdata.service;

import com.estsoft.restapiaccessdata.domain.Article;
import com.estsoft.restapiaccessdata.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article getArticleById(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("Article id " + articleId + " is not found"));
    }
}
