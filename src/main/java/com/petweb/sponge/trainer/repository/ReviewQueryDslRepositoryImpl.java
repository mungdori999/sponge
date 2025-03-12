package com.petweb.sponge.trainer.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.petweb.sponge.trainer.repository.QReviewEntity.*;

public class ReviewQueryDslRepositoryImpl implements ReviewQueryDslRepository{

    private final JPAQueryFactory queryFactory;
    private static final int PAGE_SIZE = 10;  // 페이지당 항목 수


    public ReviewQueryDslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ReviewEntity> findByTrainerId(Long trainerId, int page) {
        int offset = page * PAGE_SIZE;
        return queryFactory
                .select(reviewEntity)
                .from(reviewEntity)
                .orderBy(reviewEntity.createdAt.desc()) //최신순 정렬
                .offset(offset)
                .limit(PAGE_SIZE)
                .fetch();


    }
}
