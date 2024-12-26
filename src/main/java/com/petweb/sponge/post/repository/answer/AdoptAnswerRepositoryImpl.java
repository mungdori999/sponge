package com.petweb.sponge.post.repository.answer;

import com.petweb.sponge.post.domain.answer.AdoptAnswer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdoptAnswerRepositoryImpl implements AdoptAnswerRepository{

    private final AdoptAnswerJpaRepository adoptAnswerJpaRepository;

    @Override
    public Optional<AdoptAnswer> findByPostId(Long postId) {
        return adoptAnswerJpaRepository.findById(postId).map(AdoptAnswerEntity::toModel);
    }
}
