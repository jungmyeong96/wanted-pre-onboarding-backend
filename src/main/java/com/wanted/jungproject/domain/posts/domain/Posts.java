package com.wanted.jungproject.domain.posts.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 500, nullable = false)
    private String title;

    @NotNull
    @Column(columnDefinition = "CONTENT..", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
