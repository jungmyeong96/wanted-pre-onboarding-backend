package com.wanted.jungproject.domain.posts.dto;

import com.wanted.jungproject.domain.posts.domain.Posts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class PostsSaveRequestTest {

    @Test
    @DisplayName("[DTO 테스트] 게시물 저장 DTO 테스트")
    public void createPostsSaveRequest() {
        //Given
        PostsSaveRequest postsSaveRequest;

        //When
        postsSaveRequest = PostsSaveRequest.builder()
                .title("저장 제목")
                .content("저장 내용")
                .author("저장 저자")
                .build();

        //Then
        assertThat(postsSaveRequest.getTitle()).isEqualTo("저장 제목");
        assertThat(postsSaveRequest.getContent()).isEqualTo("저장 내용");
        assertThat(postsSaveRequest.getAuthor()).isEqualTo("저장 저자");
    }

    @Test
    public void checkToEntity() throws Exception  {
        //Given
        PostsSaveRequest postsSaveRequest;
        Posts posts;

        //When
        postsSaveRequest =  PostsSaveRequest.builder()
                .title("저장 제목")
                .content("저장 내용")
                .author("저장 저자")
                .build();

        posts = Posts.builder()
                .title("저장 제목")
                .content("저장 내용")
                .author("저장 저자")
                .build();

        //Then
        assertThat(postsSaveRequest.toEntity().getClass()).isEqualTo(posts.getClass());

    }
}