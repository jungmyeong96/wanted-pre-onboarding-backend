package com.wanted.jungproject.domain.posts.api;

import com.wanted.jungproject.domain.posts.application.IPostsService;
import com.wanted.jungproject.domain.posts.dto.PostsResponse;
import com.wanted.jungproject.domain.posts.dto.PostsSaveRequest;
import com.wanted.jungproject.domain.posts.dto.PostsUpdateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final IPostsService postsServiceImpl;
    @PostMapping("api/v1/posts")
    public Long save(@RequestBody PostsSaveRequest postsSaveRequest) {
        return postsServiceImpl.save(postsSaveRequest);
    }

    @PutMapping("api/v1/posts")
    public Long update(@RequestParam(name = "id") Long id,@RequestBody PostsUpdateRequest postsUpdateRequest) {
        return postsServiceImpl.update(id, postsUpdateRequest);
    }

    @DeleteMapping("api/v1/posts")
    public void delete(@RequestParam(name = "id") Long id) {
        postsServiceImpl.delete(id);
    }

    @GetMapping("api/v1/posts")
    public PostsResponse findById(@RequestParam(name = "id") Long id) {
        return postsServiceImpl.findById(id);
    }
}
