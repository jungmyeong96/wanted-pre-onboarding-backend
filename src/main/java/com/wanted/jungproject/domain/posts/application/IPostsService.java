package com.wanted.jungproject.domain.posts.application;

import com.wanted.jungproject.domain.posts.domain.Posts;
import com.wanted.jungproject.domain.posts.dto.PostsResponse;
import com.wanted.jungproject.domain.posts.dto.PostsSaveRequest;
import com.wanted.jungproject.domain.posts.dto.PostsUpdateRequest;

public interface IPostsService {

    public Long save(PostsSaveRequest requestSaveDto);
    public Long update(Long id, PostsUpdateRequest postsUpdateRequest);
    public void delete(Long id);
    public PostsResponse findById(Long id);
}
