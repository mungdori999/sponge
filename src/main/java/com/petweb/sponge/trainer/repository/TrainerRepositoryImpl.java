package com.petweb.sponge.trainer.repository;

import com.petweb.sponge.trainer.domain.Trainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TrainerRepositoryImpl implements TrainerRepository{
    private final TrainerJpaRepository trainerJpaRepository;
    @Override
    public Optional<Trainer> findByEmail(String email) {
        return trainerJpaRepository.findByEmail(email).map(TrainerEntity::toModel);
    }

    @Override
    public Optional<Trainer> findById(Long id) {
        return trainerJpaRepository.findTrainerById(id).map(TrainerEntity::toModel);
    }

    @Override
    public Trainer register(Trainer trainer) {
        TrainerEntity trainerEntity = TrainerEntity.builder()
                .email(trainer.getEmail())
                .name(trainer.getName())
                .build();
        return trainerJpaRepository.save(trainerEntity).toModel();
    }
}
