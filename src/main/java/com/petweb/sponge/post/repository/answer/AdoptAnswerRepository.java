package com.petweb.sponge.post.repository.answer;

import com.petweb.sponge.post.domain.answer.AdoptAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdoptAnswerRepository  {


    Optional<AdoptAnswer> findByPostId(Long postId);
}
