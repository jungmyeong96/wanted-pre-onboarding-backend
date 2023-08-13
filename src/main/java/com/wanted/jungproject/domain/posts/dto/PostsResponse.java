package com.wanted.jungproject.domain.posts.dto;

import com.wanted.jungproject.domain.posts.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PostsResponse {
    private Long id;
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsResponse(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.author = posts.getAuthor();
    }

}
