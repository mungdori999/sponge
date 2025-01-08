package com.petweb.sponge.trainer.repository;

import com.petweb.sponge.trainer.controller.response.ReviewResponse;
import com.petweb.sponge.user.repository.QUserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.petweb.sponge.trainer.repository.QReviewEntity.*;
import static com.petweb.sponge.user.repository.QUserEntity.*;

public class ReviewQueryDslRepositoryImpl implements ReviewQueryDslRepository{

    private final JPAQueryFactory queryFactory;
    private static final int PAGE_SIZE = 10;  // 페이지당 항목 수


    public ReviewQueryDslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ReviewResponse> findByTrainerId(Long trainerId, int page) {
        int offset = page * PAGE_SIZE;
        QUserEntity user = userEntity;
        QReviewEntity review = reviewEntity;

        return queryFactory
                .select(Projections.constructor(ReviewResponse.class,
                        review.id,
                        review.score,
                        review.content,
                        review.createdAt,
                        user.name
                        ))
                .from(review)
                .join(user).on(review.userId.eq(user.id))
                .orderBy(reviewEntity.createdAt.desc()) //최신순 정렬
                .offset(offset)
                .limit(PAGE_SIZE)
                .fetch();


    }
}
