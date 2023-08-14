package com.wanted.jungproject.domain.posts.application;

import com.wanted.jungproject.domain.posts.domain.Posts;
import com.wanted.jungproject.domain.posts.domain.PostsRepository;
import com.wanted.jungproject.domain.posts.dto.PostsResponse;
import com.wanted.jungproject.domain.posts.dto.PostsSaveRequest;
import com.wanted.jungproject.domain.posts.dto.PostsUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostsServiceImplTest {
    @InjectMocks
    private PostsServiceImpl postsServiceImpl;

    @Mock
    private PostsRepository postsRepository;

    @Test
    @DisplayName("[서비스 테스트] Posts 생성 테스트")
    public void createPosts() throws Exception {
        //Given
        PostsSaveRequest postsSaveRequest = createPostsSaveRequest();
        Posts posts = postsSaveRequest.toEntity();

        Long fakePostsId = 1l;
        ReflectionTestUtils.setField(posts, "id", fakePostsId);

        //mocking
        given(postsRepository.save(any()))
                .willReturn(posts);
        given(postsRepository.findById(fakePostsId))
                .willReturn(Optional.ofNullable(posts));
        //When
        Long newPostsId = postsServiceImpl.save(postsSaveRequest);

        //Then
        Posts findPosts = postsRepository.findById(newPostsId).get();

        assertThat(posts.getId()).isEqualTo(findPosts.getId());
        assertThat(posts.getTitle()).isEqualTo(findPosts.getTitle());
        assertThat(posts.getContent()).isEqualTo(findPosts.getContent());
        assertThat(posts.getAuthor()).isEqualTo(findPosts.getAuthor());
    }

    @Test
    @DisplayName("[서비스 테스트] Posts 수정 테스트")
    public void updatePosts() throws Exception {
        //Given
        PostsSaveRequest postsSaveRequest = createPostsSaveRequest();
        PostsUpdateRequest postsUpdateRequest = createPostsUpdatERequest();
        Posts posts = postsSaveRequest.toEntity();

        Long fakePostsId = 1l;
        ReflectionTestUtils.setField(posts, "id", fakePostsId);

        //mocking
        given(postsRepository.save(any()))
                .willReturn(posts);
        given(postsRepository.findById(fakePostsId))
                .willReturn(Optional.ofNullable(posts));
        //When
        Long newPostsId = postsServiceImpl.save(postsSaveRequest);
        Long updatePostsId = postsServiceImpl.update(newPostsId, postsUpdateRequest);

        //Then
        Posts findPosts = postsRepository.findById(updatePostsId).get();

        assertThat(posts.getId()).isEqualTo(findPosts.getId());
        assertThat(posts.getTitle()).isEqualTo(findPosts.getTitle());
        assertThat(posts.getContent()).isEqualTo(findPosts.getContent());
        assertThat(posts.getAuthor()).isEqualTo(findPosts.getAuthor());
    }

    @Test
    @DisplayName("[서비스 테스트] Posts 삭제 테스트")
    public void deletePosts() throws Exception {
        //Given
        PostsSaveRequest postsSaveRequest = createPostsSaveRequest();
        Posts posts = postsSaveRequest.toEntity();

        Long fakePostsId = 1l;
        ReflectionTestUtils.setField(posts, "id", fakePostsId);

        //mocking
        given(postsRepository.save(any()))
                .willReturn(posts);
        given(postsRepository.findById(fakePostsId))
                .willReturn(Optional.ofNullable(posts));
        //When
        Long newPostsId = postsServiceImpl.save(postsSaveRequest);

        //Then
        Optional<Posts> findPosts = postsRepository.findById(newPostsId);
        postsServiceImpl.delete(newPostsId);

        // Additional assertion to check if the post is marked as deleted
        assertTrue(findPosts.get().isDeleted());
    }

    @Test
    @DisplayName("[서비스 테스트] Posts ID로 찾기 테스트")
    public void findPostsById() throws Exception {
        //Given
        PostsSaveRequest postsSaveRequest = createPostsSaveRequest();
        Posts posts = postsSaveRequest.toEntity();

        Long fakePostsId = 1l;
        ReflectionTestUtils.setField(posts, "id", fakePostsId);

        //mocking
        given(postsRepository.save(any()))
                .willReturn(posts);
        given(postsRepository.findById(fakePostsId))
                .willReturn(Optional.ofNullable(posts));
        //When
        Long newPostsId = postsServiceImpl.save(postsSaveRequest);
        PostsResponse response = postsServiceImpl.findById(newPostsId);

        //Then
        verify(postsRepository, times(1)).findById(newPostsId);
        assertNotNull(response);
        assertEquals(posts.getId(), response.getId());

        // Additional assertion to check if the post is marked as deleted
    }

    @Test
    @DisplayName("[서비스 테스트] 아이디 내림차순 목록가져오기 테스트")
    public void checkFindByIdDesc() {

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