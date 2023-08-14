package com.wanted.jungproject.domain.posts.domain;

import com.wanted.jungproject.JungprojectApplication;
import com.wanted.jungproject.domain.posts.domain.Posts;
import com.wanted.jungproject.domain.posts.domain.PostsRepository;
import com.wanted.jungproject.domain.posts.dto.PostsSaveRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;

//@ContextConfiguration(classes = JungprojectApplication.class)
@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() throws Exception  {
        postsRepository.deleteAll();
    }

    private Posts makePosts() {
        return Posts.builder()
                .title("생성 제목")
                .content("생성 내용")
                .author("생성 저자")
                .build();
    }

    @Test
    @DisplayName("[레포지토리 테스트] 저장하고 모든 값 꺼내오는 테스트")
    void saveAndFindAllTest() throws Exception  {
        //Given
        Posts posts = makePosts();

        //When
        postsRepository.save(posts);

        List<Posts> postsList = postsRepository.findAll();

        //Then
        Posts post = postsList.get(0);
        assertThat(post.getTitle()).isEqualTo(posts.getTitle());
        assertThat(post.getContent()).isEqualTo(posts.getContent());
        assertThat(post.getAuthor()).isEqualTo(posts.getAuthor());
    }

    @Test
    @DisplayName("[레포지토리 테스트] 수정하고 모든 값 꺼내오는 테스트")
    void updateAndFindAllTest() throws Exception  {
        //Given
        String title = "테스트 게시글";
        String content = "테스트 내용";

        //When
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .build());

        List<Posts> postsList = postsRepository.findAll();

        //Then
        Posts post = postsList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("[레포지토리 테스트] 특정 ID값 꺼내와서 비교하는 테스트")
    void findByIdTest() throws Exception  {
        //Given
        Posts posts = makePosts();

        //When
        Long newId = postsRepository.save(posts).getId();
        Optional<Posts> newPosts = postsRepository.findById(newId);

        //Then
        if (newPosts.isPresent() == TRUE)
        {
            assertThat(posts.getTitle()).isEqualTo(newPosts.get().getTitle());
            assertThat(posts.getContent()).isEqualTo(newPosts.get().getContent());
            assertThat(posts.getAuthor()).isEqualTo(newPosts.get().getAuthor());
        }
    }

//    @Test
//    @DisplayName("[레포지토리 테스트] 특정 ID값 꺼내와서 삭제하는 테스트")
//    void deleteByIdTest() throws Exception  {
//        //Given
//        Posts posts = makePosts();
//
//        //When
//        Long newId = postsRepository.save(posts).getId();
//        Optional<Posts> targetPosts = postsRepository.findById(newId);
//
//        if (targetPosts.isPresent() == TRUE)
//        {
//            postsRepository.delete(targetPosts.get());
//        }
//        List<Posts> postsList = postsRepository.findAll();
//
//        //Then
//        assertThat(postsList.size()).isEqualTo(0);
//    }
}