package com.wanted.jungproject.domain.posts.domain;

import com.wanted.jungproject.domain.posts.domain.Posts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostsTest {

//    @Test
//    @DisplayName("[도메인 테스트] Posts 생성자 테스트")
//    public void constructPostsTest() {
//        //Given
//        Posts post = new Posts();
//
//        //When
//
//        //Then
//        assertThat(post.getTitle()).isEqualTo(null);
//        assertThat(post.getContent()).isEqualTo(null);
//    }

    @Test
    @DisplayName("[도메인 테스트] Posts 생성")
    public void createPostsTest() throws Exception {
        //Given
        Posts post;

        //When
        post = Posts.builder()
                .title("테스트 게시물 제목")
                .content("테스트 게시물 내용")
                .author("유저 이름")
                .build();

        //Then
        assertThat(post.getTitle()).isEqualTo("테스트 게시물 제목");
        assertThat(post.getContent()).isEqualTo("테스트 게시물 내용");
        assertThat(post.getAuthor()).isEqualTo("유저 이름");
    }

    @Test
    @DisplayName("[도메인 테스트] Posts 수정")
    public void updatePost() throws Exception  {
        //Given
        Posts post = Posts.builder()
                .title("테스트 게시물 제목")
                .content("테스트 게시물 내용")
                .author("유저 이름")
                .build();

        //When
        post.updatePost("수정된 테스트 게시물 제목", "수정된 테스트 게시물 내용");

        //Then
        assertThat(post.getTitle()).isEqualTo("수정된 테스트 게시물 제목");
        assertThat(post.getContent()).isEqualTo("수정된 테스트 게시물 내용");
        assertThat(post.getAuthor()).isEqualTo("유저 이름");
    }
}