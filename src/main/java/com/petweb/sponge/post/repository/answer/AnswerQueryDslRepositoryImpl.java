package com.petweb.sponge.post.repository.answer;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class AnswerQueryDslRepositoryImpl implements AnswerQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public AnswerQueryDslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<AnswerEntity> findAllAnswerWithTrainer(Long problemPostId) {
//        List<Answer> answerList = queryFactory
//                .selectFrom(answer)
//                .leftJoin(answer.problemPost,problemPost).fetchJoin()
//                .leftJoin(answer.adoptAnswer, adoptAnswer).fetchJoin()
//                .where(answer.problemPost.id.eq(problemPostId))
//                .fetch();
//        if (answerList.isEmpty()) {
//            answerList = new ArrayList<>();
//        }
//        List<Long> trainerIds = answerList.stream().map(ans -> ans.getTrainer().getId()).collect(Collectors.toList());
//
//        List<Trainer> fetch = queryFactory.selectFrom(trainer)
//                .where(trainer.id.in(trainerIds))
//                .fetch();
        return null;
    }

    @Override
    public Optional<AnswerEntity> findAnswer(Long answerId) {
//        return Optional.ofNullable(queryFactory
//                .selectFrom(answer)
//                .leftJoin(answer.problemPost, problemPost).fetchJoin()
//                .where(answer.id.eq(answerId))
//                .fetchOne());
        return null;
    }


}
