package com.wanted.jungproject.domain.posts.application;

import com.wanted.jungproject.domain.posts.domain.Posts;
import com.wanted.jungproject.domain.posts.domain.PostsRepository;
import com.wanted.jungproject.domain.posts.dto.PostsResponse;
import com.wanted.jungproject.domain.posts.dto.PostsSaveRequest;
import com.wanted.jungproject.domain.posts.dto.PostsUpdateRequest;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements IPostsService{

    @Autowired
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(@NotNull PostsSaveRequest postsSaveRequest) {

        return postsRepository.save(postsSaveRequest.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, @NotNull PostsUpdateRequest postsUpdateRequest) {

        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다!! id=" + id));
        posts.updatePost(postsUpdateRequest.getTitle(), postsUpdateRequest.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다!! id=" + id));
        posts.markAsDeleted();
    }

    public PostsResponse findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다!! id=" + id));
        return PostsResponse.builder().posts(posts).build();
    }

}
