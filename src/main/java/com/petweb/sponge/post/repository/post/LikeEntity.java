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
@Table(name = "like")
public class LikeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_post_id")
    private PostEntity postEntity;

    private Long userId;

    @Builder
    public LikeEntity(Long id, PostEntity postEntity, Long userId) {
        this.id = id;
        this.postEntity = postEntity;
        this.userId = userId;
    }
}
