package com.estsoft.restapiaccessdata.service;

import com.estsoft.restapiaccessdata.domain.Article;
import com.estsoft.restapiaccessdata.domain.Comment;
import com.estsoft.restapiaccessdata.repository.ArticleRepository;
import com.estsoft.restapiaccessdata.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    public Comment saveComment(Long articleId, Comment comment) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("Article id " + articleId + " is not found"));
        comment.setArticle(article);

        return commentRepository.save(comment);
    }

    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment id " + commentId + " is not found"));
    }

    public Comment updateComment(Comment comment) {
        Long commentId = comment.getCommentId();

        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment id " + commentId + " is not found"));
        existingComment.setBody(comment.getBody());

        return commentRepository.save(existingComment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment id " + commentId + " is not found"));

        commentRepository.deleteById(commentId);
    }
}
