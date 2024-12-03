package com.petweb.sponge.post.repository.post;

import com.petweb.sponge.post.repository.post.PostEntity;
import com.petweb.sponge.user.repository.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post_like")
public class LikeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long postId;

    private Long userId;

    @Builder
    public LikeEntity(Long id, Long postId, Long userId) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
    }
}
