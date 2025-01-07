package com.petweb.sponge.trainer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<ReviewEntity,Long> {
    @Query("SELECT r FROM ReviewEntity r LEFT JOIN FETCH r.userId WHERE r.trainerId = :trainerId")
    List<ReviewEntity> findAllByTrainerId(@Param("trainerId") Long trainerId);
}
