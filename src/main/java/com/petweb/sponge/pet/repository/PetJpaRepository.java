package com.petweb.sponge.pet.repository;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PetJpaRepository extends JpaRepository<PetEntity, Long> {

    @Query("SELECT pet FROM PetEntity pet JOIN FETCH pet.userEntity WHERE pet.userEntity.id = :loginId")
    List<PetEntity> findAllByUserId(@Param("loginId") Long loginId);

    @Query("SELECT pet FROM PetEntity pet JOIN FETCH pet.userEntity WHERE pet.id = :id")
    Optional<PetEntity> findByIdWithUser(@Param("id") Long id);
}
