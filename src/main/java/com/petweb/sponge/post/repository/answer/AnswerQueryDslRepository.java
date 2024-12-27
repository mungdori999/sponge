package com.petweb.sponge.post.repository.answer;

import java.util.List;
import java.util.Optional;

public interface AnswerQueryDslRepository {
    List<AnswerEntity> findAllAnswerWithTrainer(Long problemPostId);
    Optional<AnswerEntity> findAnswer(Long answerId);


}
