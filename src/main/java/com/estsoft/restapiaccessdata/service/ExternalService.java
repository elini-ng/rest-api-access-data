package com.estsoft.restapiaccessdata.service;

import com.estsoft.restapiaccessdata.domain.Article;
import com.estsoft.restapiaccessdata.domain.Comment;
import com.estsoft.restapiaccessdata.dto.CommentPostContent;
import com.estsoft.restapiaccessdata.dto.ArticlePostContent;
import com.estsoft.restapiaccessdata.repository.ArticleRepository;
import com.estsoft.restapiaccessdata.repository.CommentRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ExternalService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public ExternalService(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    public void getArticles() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<ArticlePostContent>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});

        if (response.getBody() != null) {
            List<ArticlePostContent> articlePostContents = response.getBody();

            for (ArticlePostContent content : articlePostContents) {
                Article article = new Article();
                article.setTitle(content.getTitle());
                article.setContent(content.getBody());

                articleRepository.save(article);
            }
        }
    }

    public void getComments() {
        String url = "https://jsonplaceholder.typicode.com/comments";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<CommentPostContent>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});

        if (response.getBody() != null) {
            List<CommentPostContent> commentPostContents = response.getBody();

            for (CommentPostContent content : commentPostContents) {
                Long articleId = content.getPostId();
                Optional<Article> article = articleRepository.findByArticleId(articleId);

                if (article.isEmpty()) {
                    continue;
                }

                Comment comment = new Comment();
                comment.setBody(content.getBody());
                comment.setArticle(article.get());

                commentRepository.save(comment);
            }
        }
    }
}
