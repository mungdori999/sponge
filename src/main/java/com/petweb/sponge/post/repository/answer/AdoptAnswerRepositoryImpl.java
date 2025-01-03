package com.petweb.sponge.post.repository.answer;

import com.petweb.sponge.post.domain.answer.AdoptAnswer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AdoptAnswerRepositoryImpl implements AdoptAnswerRepository{

    private final AdoptAnswerJpaRepository adoptAnswerJpaRepository;

    @Override
    public Optional<AdoptAnswer> findByAnswerId(Long answerId) {
        return adoptAnswerJpaRepository.findById(answerId).map(AdoptAnswerEntity::toModel);
    }

    @Override
    public List<AdoptAnswer> findListByTrainerId(Long trainerId) {
        return adoptAnswerJpaRepository.findListByTrainerId(trainerId).stream().map(AdoptAnswerEntity::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<AdoptAnswer> findByPostId(Long postId) {
        return adoptAnswerJpaRepository.findById(postId).map(AdoptAnswerEntity::toModel);
    }
}
