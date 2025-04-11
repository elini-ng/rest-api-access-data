package com.estsoft.restapiaccessdata.service;

import com.estsoft.restapiaccessdata.domain.Article;
import com.estsoft.restapiaccessdata.domain.Comment;
import com.estsoft.restapiaccessdata.dto.ArticleDTO;
import com.estsoft.restapiaccessdata.dto.CommentPostContent;
import com.estsoft.restapiaccessdata.dto.ArticlePostContent;
import com.estsoft.restapiaccessdata.repository.ArticleRepository;
import com.estsoft.restapiaccessdata.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
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
        List<ArticlePostContent> articlePostContents = response.getBody();

        for(int i = 0; i< articlePostContents.size(); i++) {
            ArticlePostContent articlePostContent = articlePostContents.get(i);

            Article article = new Article();
            article.setTitle(articlePostContent.getTitle());
            article.setContent(articlePostContent.getBody());

            articleRepository.save(article);
        }
    }

    public void getComments() {
        String url = "https://jsonplaceholder.typicode.com/comments";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<CommentPostContent>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
        List<CommentPostContent> commentPostContents = response.getBody();


        for(int i = 0; i< commentPostContents.size(); i++) {
            CommentPostContent commentPostContent = commentPostContents.get(i);

            Long articleId= commentPostContent.getPostId();
            Optional<Article> article = articleRepository.findByArticleId(articleId);

            if (article.isEmpty()) {
                continue;
            }

            Comment comment = new Comment();
            comment.setBody(commentPostContent.getBody());
            comment.setArticle(article.get());

            commentRepository.save(comment);
        }
    }
}
