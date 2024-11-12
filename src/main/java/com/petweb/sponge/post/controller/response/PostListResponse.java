package com.petweb.sponge.post.controller.response;

import com.petweb.sponge.post.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Builder
public class PostListResponse {
    private Long id;
    private String title;
    private String content;
    private Timestamp createdAt;
    private int likeCount;
    private int answerCount;
    private Long userId;
    private List<Long> categoryList;

    public static PostListResponse from(Post post) {
        return PostListResponse.builder()
                .id(post.getId())
                .title(post.getPostContent().getTitle())
                .content(post.getPostContent().getContent())
                .createdAt(post.getPostContent().getCreatedAt())
                .likeCount(post.getLikeCount())
                .answerCount(post.getAnswerCount())
                .userId(post.getUserId())
                .categoryList(post.getCategoryList())
                .build();


    }
}
