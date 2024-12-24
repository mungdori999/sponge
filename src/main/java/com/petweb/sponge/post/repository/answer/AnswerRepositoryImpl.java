package com.petweb.sponge.post.repository.answer;

import com.petweb.sponge.post.domain.answer.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AnswerRepositoryImpl implements AnswerRepository{

    private final AnswerJpaRepository answerJpaRepository;

    @Override
    public List<Answer> findListByPostId(Long postId) {
        return answerJpaRepository.findListByPostId(postId).stream().map(AnswerEntity::toModel).collect(Collectors.toList());
    }

    @Override
    public Answer save(Answer answer) {
        return answerJpaRepository.save(AnswerEntity.from(answer)).toModel();
    }
}
