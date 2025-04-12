package com.estsoft.restapiaccessdata.dto;

import com.estsoft.restapiaccessdata.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDTO {
    private Long commentId;
    private Long articleId;
    private String body;
    private LocalDateTime createdAt;

    public CommentDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.articleId = comment.getArticle().getArticleId();
        this.body = comment.getBody();
        this.createdAt = comment.getCreatedAt();
    }
}
