package com.petweb.sponge.post.repository.post;

import com.petweb.sponge.post.domain.ProblemType;
import com.petweb.sponge.post.repository.post.PostEntity;
import com.petweb.sponge.utils.ProblemTypeCode;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_type_id")
    private ProblemType problemType;
    @Builder
    public PostCategoryEntity(PostEntity postEntity, ProblemType problemType) {
        this.postEntity = postEntity;
        this.problemType = problemType;
    }
    public String toModel() {
        return problemType.getDescription();
    }
}
