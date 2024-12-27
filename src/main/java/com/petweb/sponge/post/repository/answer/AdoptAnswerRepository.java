package com.petweb.sponge.post.repository.answer;

import com.petweb.sponge.post.domain.answer.AdoptAnswer;

import java.util.Optional;

public interface AdoptAnswerRepository  {


    Optional<AdoptAnswer> findByAnswerId(Long answerId);
    Optional<AdoptAnswer> findByPostId(Long postId);

}
