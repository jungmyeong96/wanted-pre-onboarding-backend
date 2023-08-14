package com.wanted.jungproject.domain.posts.api;

import com.wanted.jungproject.domain.posts.domain.Posts;
import com.wanted.jungproject.domain.posts.domain.PostsRepository;
import com.wanted.jungproject.domain.posts.dto.PostsResponse;
import com.wanted.jungproject.domain.posts.dto.PostsSaveRequest;
import com.wanted.jungproject.domain.posts.dto.PostsUpdateRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    @LocalServerPort
    private  int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("[컨트롤러 테스트] Posts 생성 테스트")
    public void createPosts() throws Exception {
        //Given
        PostsSaveRequest postsSaveRequest = createPostsSaveRequest();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //When
        ResponseEntity<Long> responseEntity = restTemplate.
                postForEntity(url, postsSaveRequest, Long.class);


        //Then
        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(postsSaveRequest.getTitle());
        assertThat(all.get(0).getContent()). isEqualTo(postsSaveRequest.getContent());
    }

    @Test
    @DisplayName("[컨트롤러 테스트] Posts 수정 테스트")
    public void updatePosts() throws Exception {
        //Given
        Posts savedPosts = postsRepository.save(makePosts());

        Long updatedId = savedPosts.getId();
        PostsUpdateRequest postsUpdateRequest =
                createPostsUpdatERequest();

        String url = "http://localhost:" + port + "/api/v1/posts?id="+updatedId;
        HttpEntity<PostsUpdateRequest> requestEntity =
                new HttpEntity<>(postsUpdateRequest);

        //when

        ResponseEntity<Long> responseEntity = restTemplate.
                exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //Then
        assertThat(responseEntity.getStatusCode()).
                isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.get(0).getTitle()).
                isEqualTo(postsUpdateRequest.getTitle());
        assertThat(all.get(0).getContent()).
                isEqualTo(postsUpdateRequest.getContent());
    }

    @Test
    @DisplayName("[컨트롤러 테스트] Posts 삭제 테스트")
    public void deletePosts() throws Exception {
        //Given
        Posts savedPost = postsRepository.save(makePosts());
        Long id = savedPost.getId();

        String url = "http://localhost:" + port + "/api/v1/posts?id="+ id;
        HttpEntity<Long> requestEntity =
                new HttpEntity<>(id);

        //When
        ResponseEntity<Void> responseEntity = restTemplate.
                exchange(url, HttpMethod.DELETE, requestEntity, Void.class);

        //Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(postsRepository.findById(savedPost.getId())).isPresent();


        // Additional assertion to check if the post is marked as deleted

    }

    @Test
    @DisplayName("[컨트롤러 테스트] Posts ID로 찾기 테스트")
    public void findPostsById() throws Exception {
        //Given
        Posts savedPost = postsRepository.save(makePosts());
        Long id = savedPost.getId();

        String url = "http://localhost:" + port + "/api/v1/posts?id="+ id;
        //When
        ResponseEntity<PostsResponse> responseEntity = restTemplate.getForEntity(
                url,
                PostsResponse.class
        );
        //Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        PostsResponse response = responseEntity.getBody();
        assertThat(response).isNotNull();
    }

    private PostsSaveRequest createPostsSaveRequest() {
        return PostsSaveRequest.builder()
                .title("생성 제목")
                .content("생성 내용")
                .author("생성 저자")
                .build();
    }

    private PostsUpdateRequest createPostsUpdatERequest() {
        return PostsUpdateRequest.builder()
                .title("생성 제목")
                .content("생성 내용")
                .build();
    }

    private Posts makePosts() {
        return Posts.builder()
                .title("생성 제목")
                .content("생성 내용")
                .author("생성 저자")
                .build();
    }

}