package com.wanted.jungproject.domain.posts.domain;


import com.wanted.jungproject.domain.posts.dto.PostsSaveRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE,
        connection = EmbeddedDatabaseConnection.H2)
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() throws Exception  {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("[레포지토리 테스트] 저장하기 테스트")
    void saveAndFindAllTest() throws Exception  {
        //Given
        Posts posts = createPostsSaveRequest().toEntity();

        //When
        Posts newPosts = postsRepository.save(posts);

        //Then
        assertThat(posts).isSameAs(newPosts);
        assertThat(posts.getTitle()).isEqualTo(newPosts.getTitle());
        assertThat(posts.getContent()).isEqualTo(newPosts.getContent());
        assertThat(posts.getAuthor()).isEqualTo(newPosts.getAuthor());
    }

    @Test
    @DisplayName("[레포지토리 테스트] 값 가져오기 테스트")
    void findByIdTest() throws Exception  {
        //Given
        Posts posts = postsRepository.save(createPostsSaveRequest().toEntity());

        //When
        Posts findPosts = postsRepository.findById(posts.getId())
                .orElseThrow(() -> new IllegalArgumentException("찾는 값이 없습니다.! id="+ posts.getId()));

        //Then
        assertThat(postsRepository.count()).isEqualTo(1);
        assertThat(findPosts.getTitle()).isEqualTo(posts.getTitle());
        assertThat(findPosts.getContent()).isEqualTo(posts.getContent());
        assertThat(findPosts.getAuthor()).isEqualTo(posts.getAuthor());

    }

    private PostsSaveRequest createPostsSaveRequest() {
        return PostsSaveRequest.builder()
                .title("생성 제목")
                .content("생성 내용")
                .author("생성 저자")
                .build();
    }
}