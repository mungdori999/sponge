package com.petweb.sponge.trainer.repository;

import com.petweb.sponge.trainer.controller.response.ReviewResponse;
import com.petweb.sponge.trainer.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Optional<Review> findByUserId(Long loginId, Long trainerId);
    List<ReviewResponse> findListByTrainerId(Long trainerId, int page);
    void save(Review review);

}
