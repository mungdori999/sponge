package com.petweb.sponge.post.repository.answer;

import com.petweb.sponge.post.domain.answer.AdoptAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdoptAnswerRepository extends JpaRepository<AdoptAnswer,Long> {

    @Query("SELECT adn FROM AdoptAnswer adn JOIN FETCH adn.trainerEntity WHERE adn.answer.id = :answerId AND adn.trainerEntity.id = :trainerId")
    Optional<AdoptAnswer> findAdoptAnswer(@Param("answerId") Long answerId, @Param("trainerId") Long trainerId);
}
