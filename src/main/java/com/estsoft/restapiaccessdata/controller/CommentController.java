package com.estsoft.restapiaccessdata.controller;

import com.estsoft.restapiaccessdata.domain.Comment;
import com.estsoft.restapiaccessdata.dto.CommentResponseDto;
import com.estsoft.restapiaccessdata.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentResponseDto> addComment(@PathVariable("articleId") Long articleId,
                                         @RequestBody Comment comment) {
        Comment savedComment = commentService.saveComment(articleId, comment);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new CommentResponseDto(savedComment));
    }

    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable("commentId") Long commentId) {
        Comment comment = commentService.getComment(commentId);
        return ResponseEntity.ok(new CommentResponseDto(comment));
    }

    @PutMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable("commentId") Long commentId,
                                            @RequestBody Comment comment) {
        comment.setCommentId(commentId);

        return ResponseEntity.ok(new CommentResponseDto(commentService.updateComment(comment)));
    }

    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
