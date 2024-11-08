package com.petweb.sponge.post.domain.post;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class PostContent {

    private String title;
    private String content;
    private String duration;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

    @Builder
    public PostContent(String title, String content, String duration, Timestamp createdAt, Timestamp modifiedAt) {
        this.title = title;
        this.content = content;
        this.duration = duration;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
