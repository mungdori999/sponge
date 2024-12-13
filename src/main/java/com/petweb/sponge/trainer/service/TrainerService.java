package com.petweb.sponge.trainer.service;

import com.petweb.sponge.exception.error.NotFoundTrainer;
import com.petweb.sponge.trainer.domain.Review;
import com.petweb.sponge.trainer.domain.Trainer;
import com.petweb.sponge.trainer.repository.TrainerEntity;
import com.petweb.sponge.trainer.dto.*;
import com.petweb.sponge.trainer.repository.ReviewRepository;
import com.petweb.sponge.trainer.repository.TrainerRepository;
import com.petweb.sponge.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;


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
     * @param trainerDetailDTO
     * @return
     */
    @Transactional
    public void saveTrainer(TrainerDetailDTO trainerDetailDTO) {
        //로그인하자마자 저장 되어있던 trainer 조회
//        TrainerEntity trainerEntity = trainerRepository.findById(loginId).orElseThrow(
//                NotFoundTrainer::new);
//        //trainer에 정보 셋팅 및 저장
//        trainerEntity.settingTrainer(trainerDetailDTO);
//        trainerRepository.save(trainerEntity);
    }

    /**
     * 훈련사 정보 수정
     *
     * @param trainerId
     * @param trainerDetailDTO
     */
    @Transactional
    public void updateTrainer(Long trainerId, TrainerDetailDTO trainerDetailDTO) {
//        TrainerEntity trainerEntity = trainerRepository.findById(trainerId).orElseThrow(
//                NotFoundTrainer::new);
//        trainerRepository.initTrainer(trainerId);
//        //trainer 정보 수정
//        trainerEntity.settingTrainer(trainerDetailDTO);
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

    /**
     * 리뷰 데이터 조회
     *
     * @param trainerId
     * @return
     */
    @Transactional(readOnly = true)
    public List<ReviewDetailDTO> findAllReview(Long trainerId) {
        List<Review> reviewList = reviewRepository.findAllByTrainerId(trainerId);
        return reviewList.stream().map(review -> ReviewDetailDTO.builder()
                .userId(review.getUserEntity().getId())
                .userName(review.getUserEntity().getName())
                .score(review.getScore())
                .content(review.getContent()).build()).collect(Collectors.toList());
    }

    /**
     * 리뷰 데이터 저장
     *
     * @param reviewDTO
     * @param loginId
     */
    @Transactional
    public void saveReview(ReviewDTO reviewDTO, Long loginId) {
//        UserEntity userEntity = userRepository.findById(loginId).orElseThrow(
//                NotFoundUser::new);
//        Trainer trainer = trainerRepository.findById(loginId).orElseThrow(
//                NotFoundTrainer::new);
//
//        Review review = Review.builder()
//                .score(reviewDTO.getScore())
//                .content(reviewDTO.getContent())
//                .userEntity(userEntity)
//                .trainer(trainer)
//                .build();
//        reviewRepository.save(review);
    }



}
