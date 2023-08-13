package com.wanted.jungproject.domain.posts.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequest {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
