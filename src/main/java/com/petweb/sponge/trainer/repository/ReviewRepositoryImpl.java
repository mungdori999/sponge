package com.petweb.sponge.trainer.repository;

import com.petweb.sponge.trainer.controller.response.ReviewResponse;
import com.petweb.sponge.trainer.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {
    private final ReviewJpaRepository reviewJpaRepository;

    @Override
    public Optional<Review> findByUserId(Long loginId, Long trainerId) {
        return reviewJpaRepository.findByUserId(loginId, trainerId).map(ReviewEntity::toModel);
    }

    @Override
    public List<ReviewResponse> findListByTrainerId(Long trainerId, int page) {
        return reviewJpaRepository.findByTrainerId(trainerId,page);
    }

    @Override
    public void save(Review review) {
        reviewJpaRepository.save(ReviewEntity.from(review));
    }
}
