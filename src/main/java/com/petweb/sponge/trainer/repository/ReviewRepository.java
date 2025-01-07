package com.petweb.sponge.trainer.repository;

import com.petweb.sponge.trainer.domain.Review;

import java.util.Optional;

public interface ReviewRepository {
    Optional<Review> findByUserId(Long loginId, Long trainerId);
    void save(Review review);

}
