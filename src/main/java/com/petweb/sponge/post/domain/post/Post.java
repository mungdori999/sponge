package com.petweb.sponge.post.domain.post;


import com.petweb.sponge.post.dto.post.PostCreate;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

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
    private List<Long> categoryList;

    @Builder
    public Post(Long id, PostContent postContent, int likeCount, int answerCount, Long userId, Long petId, List<PostFile> postFileList, List<Tag> tagList, List<Long> categoryList) {
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

    public static Post from(Long userId, Long petId, PostCreate postCreate) {
        return Post.builder()
                .postContent(PostContent.builder().title(postCreate.getTitle())
                        .content(postCreate.getContent())
                        .duration(postCreate.getDuration())
                        .build())
                .userId(userId)
                .petId(petId)
                .postFileList(postCreate.getFileUrlList().stream().map((fileUrl) -> PostFile.builder().fileUrl(fileUrl).build()).collect(Collectors.toList()))
                .tagList(postCreate.getHasTagList().stream().map((hasTag) -> Tag.builder().hashtag(hasTag).build()).collect(Collectors.toList()))
                .categoryList(postCreate.getCategoryList())
                .build();
    }
}
