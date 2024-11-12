package com.petweb.sponge.post.repository.post;

import com.petweb.sponge.post.domain.ProblemType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post_category")
public class PostCategoryEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long categoryCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;



    @Builder
    public PostCategoryEntity(PostEntity postEntity, Long categoryCode) {
        this.postEntity = postEntity;
        this.categoryCode = categoryCode;
    }


    public Long toModel() {
        return categoryCode;
    }

}
