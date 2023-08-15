package com.wanted.jungproject.domain.posts.api;

import com.wanted.jungproject.domain.paging.domain.PageInfo;
import com.wanted.jungproject.domain.paging.dto.AllPageInfo;
import com.wanted.jungproject.domain.posts.application.IPostsService;
import com.wanted.jungproject.domain.posts.domain.Posts;
import com.wanted.jungproject.domain.posts.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Posts", description = "posts API Document")
public class PostsApiController {

    private final IPostsService postsServiceImpl;
    @PostMapping("api/v1/posts")
    @Operation(summary = "게시물 저장", description = "게시물 정보를 DB에 저장합니다.", tags = {"Posts"})
    public Long save(@RequestBody PostsSaveRequest postsSaveRequest) {
        return postsServiceImpl.save(postsSaveRequest);
    }

    @PutMapping("api/v1/posts")
    @Operation(summary = "게시물 수정", description = "게시물 정보를 수정합니다.", tags = {"Posts"})
    public Long update(@RequestParam(name = "id") Long id,@RequestBody PostsUpdateRequest postsUpdateRequest) {
        return postsServiceImpl.update(id, postsUpdateRequest);
    }

    @DeleteMapping("api/v1/posts")
    @Operation(summary = "게시물 수정", description = "게시물 정보를 삭제합니다.", tags = {"Posts"})
    public void delete(@RequestParam(name = "id") Long id) {
        postsServiceImpl.delete(id);
    }

    @GetMapping("api/v1/posts")
    @Operation(summary = "게시물 정보", description = "특정 게시물 정보를 요청합니다.", tags = {"Posts"})
    public PostsResponse findById(@RequestParam(name = "id") Long id) {
        return postsServiceImpl.findById(id);
    }

    @GetMapping("api/v1/postslist")
    @Operation(summary = "게시물 목록", description = "게시물 목록 정보를 요청합니다.", tags = {"Posts"})
    public ResponseEntity getPostsList(@Positive @RequestParam int page, @Positive @RequestParam int size) {
        Page<Posts> postsPage = postsServiceImpl.findPosts(page-1, size);
        PageInfo pageInfo = new PageInfo(page, size, (int) postsPage.getTotalElements(), (int) postsPage.getTotalPages());

        List<Posts> posts = postsPage.getContent();
        List<PostsListResponse> postsListResponses = posts.stream().map(PostsListResponse::new).collect(Collectors.toList());

        return new ResponseEntity<>(new AllPageInfo(postsListResponses, pageInfo), HttpStatus.OK);

    }
}
