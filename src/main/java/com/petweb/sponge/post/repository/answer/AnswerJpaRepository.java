package com.petweb.sponge.post.repository.answer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerJpaRepository extends JpaRepository<AnswerEntity,Long>, AnswerQueryDslRepository {
}
