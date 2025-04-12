package com.estsoft.restapiaccessdata.dto;

import com.estsoft.restapiaccessdata.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private Long articleId;
    private String body;
    private LocalDateTime createdAt;
    private ArticleDTO article;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.articleId = comment.getArticle().getArticleId();
        this.body = comment.getBody();
        this.createdAt = comment.getCreatedAt();
        this.article = new ArticleDTO(comment.getArticle());
    }
}
