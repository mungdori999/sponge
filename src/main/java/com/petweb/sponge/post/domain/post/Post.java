package com.petweb.sponge.post.domain.post;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Post {
    private Long id;
    private PostContent postContent;
    private int likeCount;
    private int answerCount;
    private Long userId;
    private Long petId;
    private List<PostFile> postFileList;
    private List<Tag> tagList;
    private List<String> categoryList;

    @Builder
    public Post(Long id, PostContent postContent, int likeCount, int answerCount, Long userId, Long petId, List<PostFile> postFileList, List<Tag> tagList, List<String> categoryList) {
        this.id = id;
        this.postContent = postContent;
        this.likeCount = likeCount;
        this.answerCount = answerCount;
        this.userId = userId;
        this.petId = petId;
        this.postFileList = postFileList;
        this.tagList = tagList;
        this.categoryList = categoryList;
    }
}
