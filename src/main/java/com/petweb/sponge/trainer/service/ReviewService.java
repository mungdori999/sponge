package com.petweb.sponge.trainer.service;

import com.petweb.sponge.exception.error.NotFoundUser;
import com.petweb.sponge.trainer.controller.response.ReviewCheckResponse;
import com.petweb.sponge.trainer.domain.Review;
import com.petweb.sponge.trainer.dto.ReviewCreate;
import com.petweb.sponge.trainer.repository.ReviewRepository;
import com.petweb.sponge.user.domain.User;
import com.petweb.sponge.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;


    /**
     * 내가 이 훈련사에게 리뷰를 썼는지 체크
     *
     * @param loginId
     * @param trainerId
     * @return
     */
    public ReviewCheckResponse findCheck(Long loginId, Long trainerId) {
        Optional<Review> review = reviewRepository.findByUserId(loginId, trainerId);
        return ReviewCheckResponse.from(review);
    }

    /**
     * 리뷰 데이터 생성
     *
     * @param loginId
     * @param reviewCreate
     */
    @Transactional
    public void create(Long loginId, ReviewCreate reviewCreate) {
        User user = userRepository.findById(loginId).orElseThrow(
                NotFoundUser::new);
        Review review = Review.from(user.getId(), reviewCreate);
        reviewRepository.save(review);
    }


}
