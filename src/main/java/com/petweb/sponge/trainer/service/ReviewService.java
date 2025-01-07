package com.petweb.sponge.trainer.service;

import com.petweb.sponge.exception.error.NotFoundUser;
import com.petweb.sponge.trainer.domain.Review;
import com.petweb.sponge.trainer.dto.ReviewCreate;
import com.petweb.sponge.trainer.repository.ReviewJpaRepository;
import com.petweb.sponge.trainer.repository.ReviewRepository;
import com.petweb.sponge.user.domain.User;
import com.petweb.sponge.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Transactional
    public void create(Long loginId, ReviewCreate reviewCreate) {
        User user = userRepository.findById(loginId).orElseThrow(
                NotFoundUser::new);
        Review review = Review.from(user.getId(), reviewCreate);
        reviewRepository.save(review);
    }
}
