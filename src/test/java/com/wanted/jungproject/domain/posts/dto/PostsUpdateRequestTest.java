package com.wanted.jungproject.domain.posts.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PostsUpdateRequestTest {

    @Test
    @DisplayName("[DTO 테스트] 게시물 업데이트 DTO 테스트")
    public void createPostsUpdateRequest() {

        //Given
        PostsUpdateRequest postsUpdateRequest;

        //when
        postsUpdateRequest = PostsUpdateRequest.builder()
                .title("업데이트 타이틀")
                .content("업데이트 내용")
                .build();

        //Then
        assertThat(postsUpdateRequest.getTitle()).isEqualTo("업데이트 타이틀");
        assertThat(postsUpdateRequest.getContent()).isEqualTo("업데이트 내용");
    }
}