package com.wanted.jungproject.domain.paging.dto;
import com.wanted.jungproject.domain.paging.domain.PageInfo;
import lombok.Getter;

@Getter
public class AllPageInfo<T> {

    private T data;
    private PageInfo pageInfo;

    public AllPageInfo(T data, PageInfo pageInfo) {
        this.data = data;
        this.pageInfo = pageInfo;
    }

}
