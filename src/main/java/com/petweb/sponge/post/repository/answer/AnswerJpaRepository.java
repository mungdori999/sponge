package com.petweb.sponge.post.repository.answer;

import com.petweb.sponge.post.domain.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerJpaRepository extends JpaRepository<AnswerEntity,Long>, AnswerQueryDslRepository {
    @Query("SELECT a FROM AnswerEntity a WHERE a.postId = :postId")
    List<AnswerEntity> findListByPostId(@Param("postId") Long postId);
}
