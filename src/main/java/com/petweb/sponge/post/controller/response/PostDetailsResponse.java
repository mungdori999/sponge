package com.petweb.sponge.post.controller.response;

import com.petweb.sponge.pet.controller.response.PetResponse;
import com.petweb.sponge.pet.domain.Pet;
import com.petweb.sponge.post.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class PostDetailsResponse {
    private Long id;
    private String title;
    private String content;
    private String duration;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private int likeCount;
    private int answerCount;
    private Long userId;
    private PetResponse pet;
    private List<PostFileResponse> postFileList;
    private List<TagResponse> tagList;
    private List<Long> categoryList;

    public static PostDetailsResponse from(Post post, Pet pet) {
        return PostDetailsResponse.builder()
                .id(post.getId())
                .title(post.getPostContent().getTitle())
                .content(post.getPostContent().getContent())
                .duration(post.getPostContent().getDuration())
                .createdAt(post.getPostContent().getCreatedAt())
                .modifiedAt(post.getPostContent().getModifiedAt())
                .likeCount(post.getLikeCount())
                .answerCount(post.getAnswerCount())
                .userId(post.getUserId())
                .pet(PetResponse.from(pet))
                .postFileList(post.getPostFileList().stream().map(PostFileResponse::from).collect(Collectors.toList()))
                .tagList(post.getTagList().stream().map(TagResponse::from).collect(Collectors.toList()))
                .categoryList(post.getCategoryList())
                .build();


    }
}
