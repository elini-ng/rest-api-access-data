package com.estsoft.restapiaccessdata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentPostContent {
    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;
}
