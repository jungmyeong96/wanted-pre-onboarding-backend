package com.wanted.jungproject.domain.posts.api;

import com.wanted.jungproject.domain.paging.domain.PageInfo;
import com.wanted.jungproject.domain.posts.application.IPostsService;
import com.wanted.jungproject.domain.posts.domain.Posts;
import com.wanted.jungproject.domain.posts.dto.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


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

    @GetMapping("api/v1/postslist")
    public ResponseEntity getPostsList(@Positive @RequestParam int page, @Positive @RequestParam int size) {
        Page<Posts> postsPage = postsServiceImpl.findPosts(page-1, size);
        PageInfo pageInfo = new PageInfo(page, size, (int) postsPage.getTotalElements(), (int) postsPage.getTotalPages());

        List<Posts> posts = postsPage.getContent();
        List<PostsListResponse> postsListResponses = posts.stream().map(PostsListResponse::new).collect(Collectors.toList());

        return new ResponseEntity<>(new PostsAllPageInfo(postsListResponses, pageInfo), HttpStatus.OK);

    }
}
