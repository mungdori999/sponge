package com.petweb.sponge.post.repository.answer;

import com.petweb.sponge.post.domain.answer.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AnswerRepositoryImpl implements AnswerRepository{

    private final AnswerJpaRepository answerJpaRepository;

    @Override
    public Answer save(Answer answer) {
        return answerJpaRepository.save(AnswerEntity.from(answer)).toModel();
    }
}
