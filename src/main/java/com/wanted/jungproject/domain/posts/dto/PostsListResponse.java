package com.wanted.jungproject.domain.posts.dto;

import com.wanted.jungproject.domain.posts.domain.Posts;
import lombok.Getter;


@Getter
public class PostsListResponse {

    private Long id;
    private String title;
    private String author;

    public PostsListResponse(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
    }
}