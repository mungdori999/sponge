package com.petweb.sponge.trainer.repository;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TrainerJpaRepository extends JpaRepository<TrainerEntity,Long>,TrainerQueryDslRepository {

    @Query("SELECT trainer FROM TrainerEntity trainer where trainer.email = :email")
    Optional<TrainerEntity> findByEmail(@Param("email") String email);
}
