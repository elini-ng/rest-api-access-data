package com.estsoft.restapiaccessdata.repository;

import com.estsoft.restapiaccessdata.domain.Article;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @EntityGraph(attributePaths = "comments")
    Optional<Article> findByArticleId(Long articleId);
}
