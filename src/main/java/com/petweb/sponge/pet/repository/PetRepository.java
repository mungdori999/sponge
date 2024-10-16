package com.petweb.sponge.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<PetEntity,Long> {

    List<PetEntity> findAllByUserEntityId(Long loginId);
}
