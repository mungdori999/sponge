package com.petweb.sponge.post.repository.answer;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static com.petweb.sponge.post.repository.answer.QAnswerEntity.*;

public class AnswerQueryDslRepositoryImpl implements AnswerQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public AnswerQueryDslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private static final int PAGE_SIZE = 10;  // 페이지당 항목 수
    @Override
    public List<AnswerEntity> findListByTrainerId(Long loginId, int page) {
        // 페이지 번호와 페이지 크기를 계산
        int offset = page * PAGE_SIZE;
         return queryFactory.select(answerEntity)
                 .from(answerEntity)
                 .where(answerEntity.trainerId.eq(loginId))
                 .orderBy(answerEntity.createdAt.desc())
                 .offset(offset)
                 .limit(PAGE_SIZE)
                 .fetch();
    }
}
