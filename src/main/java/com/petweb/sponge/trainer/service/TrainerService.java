package com.petweb.sponge.trainer.service;

import com.petweb.sponge.exception.error.NotFoundTrainer;
import com.petweb.sponge.trainer.domain.Trainer;
import com.petweb.sponge.trainer.dto.*;
import com.petweb.sponge.trainer.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;


    /**
     * 훈련사 단건 조회
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Trainer getById(Long id) {
        return trainerRepository.findById(id).orElseThrow(
                NotFoundTrainer::new);
    }

    /**
     * 내정보 조회
     *
     * @param loginId
     * @return
     */
    @Transactional(readOnly = true)
    public Trainer findMyInfo(Long loginId) {
        return trainerRepository.findById(loginId).orElseThrow(
                NotFoundTrainer::new);
    }


    /**
     * 훈련사 정보저장
     *
     * @return
     */
    @Transactional
    public Trainer create(TrainerCreate trainerCreate) {
        Trainer trainer = Trainer.from(trainerCreate);
        return trainerRepository.save(trainer);
    }

    /**
     * 훈련사 정보 수정
     *
     * @param id
     * @param trainerUpdate
     */
    @Transactional
    public void update(Long id, TrainerUpdate trainerUpdate) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(
                NotFoundTrainer::new);
        trainerRepository.initTrainer(id);
        trainer = trainer.update(trainerUpdate);
        trainer = trainerRepository.save(trainer);
    }

    /**
     * 훈련사 정보 삭제
     *
     * @param trainerId
     */
    @Transactional
    public void deleteTrainer(Long trainerId) {
//        TrainerEntity trainerEntity = trainerRepository.findById(trainerId).orElseThrow(
//                NotFoundTrainer::new);
//
//        //벌크성 쿼리로 history, address 한번에 삭제
//        trainerRepository.deleteTrainer(trainerEntity.getId());
    }

    /**
     * 훈련사 이미지 삭제
     *
     * @param trainerId
     */
    @Transactional
    public void deleteTrainerImg(Long trainerId) {
//        TrainerEntity trainerEntity = trainerRepository.findById(trainerId).orElseThrow(
//                NotFoundTrainer::new);
//        trainerEntity.setProfileImgUrl(null);
    }


}
