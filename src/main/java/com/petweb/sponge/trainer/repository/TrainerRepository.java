package com.petweb.sponge.trainer.repository;

import com.petweb.sponge.trainer.domain.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository {

    Optional<Trainer> findByEmail(String email);
    List<Trainer> findShortById(List<Long> trainerIdList);
    Optional<Trainer> findById(Long id);
    Trainer save(Trainer trainer);

    void initTrainer(Long id);

}
