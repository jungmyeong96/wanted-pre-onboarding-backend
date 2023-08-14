package com.wanted.jungproject.domain.posts.dto;

import com.wanted.jungproject.domain.paging.domain.PageInfo;
import lombok.Getter;

@Getter
public class PostsAllPageInfo<T> {

    private T data;
    private PageInfo pageInfo;

    public PostsAllPageInfo(T data, PageInfo pageInfo) {
        this.data = data;
        this.pageInfo = pageInfo;
    }

}
