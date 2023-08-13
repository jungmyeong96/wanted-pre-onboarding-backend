package com.wanted.jungproject.domain.posts.dto;

import com.wanted.jungproject.domain.posts.domain.Posts;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PostsResponseTest {

    @Test
    public void createPostsResponseTest() {
        //Given
        PostsResponse postsResponse;

        Posts post;
        post = Posts.builder()
                .title("응답 제목")
                .content("응답 내용")
                .author("응답 저자")
                .build();

        //When
        postsResponse = PostsResponse.builder()
                .posts(post)
                .build();

        //Then
        assertThat(postsResponse.getId()).isNotNull();
        assertThat(postsResponse.getTitle()).isEqualTo("응답 제목");
        assertThat(postsResponse.getContent()).isEqualTo("응답 내용");
        assertThat(postsResponse.getAuthor()).isEqualTo("응답 저자");
    }
}