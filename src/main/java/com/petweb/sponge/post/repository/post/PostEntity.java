package com.petweb.sponge.post.repository.post;

import com.petweb.sponge.pet.repository.PetEntity;
import com.petweb.sponge.post.domain.post.Post;
import com.petweb.sponge.post.domain.post.PostContent;
import com.petweb.sponge.post.domain.post.PostFile;
import com.petweb.sponge.post.domain.post.Tag;
import com.petweb.sponge.user.repository.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class)
public class PostEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String title; // 글제목
    private String content; // 글내용
    private String duration; // 문제행동 지속기간
    private int likeCount; // 추천수
    private int answerCount; // 답변수
    @CreatedDate
    private Timestamp createdAt;

    @LastModifiedDate
    private Timestamp modifiedAt;
    private Long userId;

    private Long petId;


    @OneToMany(mappedBy = "postEntity", cascade = CascadeType.ALL)
    private List<PostCategoryEntity> postCategoryEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "postEntity", cascade = CascadeType.ALL)
    private List<TagEntity> tagEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "postEntity", cascade = CascadeType.ALL)
    private List<PostFileEntity> postFileEntityList = new ArrayList<>();

    public void addPostFileList(List<PostFileEntity> postFileEntityList) {
        this.postFileEntityList = postFileEntityList;
    }

    public void addTagList(List<TagEntity> tagEntityList) {
        this.tagEntityList = tagEntityList;
    }

    public void addPostCategoryList(List<PostCategoryEntity> postCategoryEntityList) {
        this.postCategoryEntityList = postCategoryEntityList;
    }

    public Post toModel() {

        List<PostFile> postFileList = (!Hibernate.isInitialized(postFileEntityList) || postFileEntityList == null || postFileEntityList.isEmpty())
                ? Collections.emptyList()
                : postFileEntityList.stream()
                .map(PostFileEntity::toModel)
                .collect(Collectors.toList());


        List<Tag> tagList = (!Hibernate.isInitialized(tagEntityList) || tagEntityList == null || tagEntityList.isEmpty())
                ? Collections.emptyList()
                : tagEntityList.stream()
                .map(TagEntity::toModel)
                .collect(Collectors.toList());

        List<Long> categoryList = (!Hibernate.isInitialized(postCategoryEntityList) || postCategoryEntityList == null || postCategoryEntityList.isEmpty())
                ? Collections.emptyList()
                : postCategoryEntityList.stream()
                .map(PostCategoryEntity::toModel)
                .collect(Collectors.toList());


        return Post.builder()
                .id(id)
                .postContent(PostContent.builder().title(title).content(content).duration(duration).createdAt(createdAt).modifiedAt(modifiedAt).build())
                .likeCount(likeCount)
                .answerCount(answerCount)
                .userId(userId)
                .petId(petId)
                .postFileList(postFileList)
                .tagList(tagList)
                .categoryList(categoryList)
                .build();
    }

    public static PostEntity from(Post post) {
        PostEntity postEntity = new PostEntity();
        postEntity.title = post.getPostContent().getTitle();
        postEntity.content = post.getPostContent().getContent();
        postEntity.duration = post.getPostContent().getDuration();
        postEntity.likeCount = post.getLikeCount();
        postEntity.answerCount = post.getAnswerCount();
        postEntity.userId = post.getUserId();
        postEntity.petId = post.getPetId();

        postEntity.tagEntityList = post.getTagList().stream()
                .map(tag -> TagEntity.builder()
                        .hashtag(tag.getHashtag())
                        .postEntity(postEntity)
                        .build())
                .collect(Collectors.toList());
        postEntity.postCategoryEntityList = post.getCategoryList().stream().map(category -> PostCategoryEntity.builder()
                .categoryCode(category)
                .postEntity(postEntity).build()
        ).collect(Collectors.toList());
        postEntity.postFileEntityList = post.getPostFileList().stream().map(postFile -> PostFileEntity.builder()
                .fileUrl(postFile.getFileUrl())
                .postEntity(postEntity).build()).collect(Collectors.toList());
        return postEntity;
    }
}
