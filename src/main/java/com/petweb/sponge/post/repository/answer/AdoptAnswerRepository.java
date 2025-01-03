package com.petweb.sponge.post.repository.answer;

import com.petweb.sponge.post.domain.answer.AdoptAnswer;

import java.util.List;
import java.util.Optional;

public interface AdoptAnswerRepository  {


    Optional<AdoptAnswer> findByAnswerId(Long answerId);
    List<AdoptAnswer> findListByTrainerId(Long trainerId);
    Optional<AdoptAnswer> findByPostId(Long postId);
    void save(AdoptAnswer adoptAnswer);

    void delete(AdoptAnswer adoptAnswer);

}
