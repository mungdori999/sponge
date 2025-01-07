package com.petweb.sponge.trainer.controller;

import com.petweb.sponge.auth.UserAuth;
import com.petweb.sponge.trainer.controller.response.ReviewCheckResponse;
import com.petweb.sponge.trainer.dto.ReviewCreate;
import com.petweb.sponge.trainer.service.ReviewService;
import com.petweb.sponge.utils.AuthorizationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final AuthorizationUtil authorizationUtil;

    /**
     * 내가 이 훈련사에게 리뷰를 썼는지 체크
     */
    @GetMapping
    public ResponseEntity<ReviewCheckResponse> findCheck(@RequestParam("trainerId")Long trainerId) {
        ReviewCheckResponse reviewCheckResponse = reviewService.findCheck(authorizationUtil.getLoginId(), trainerId);
        return new ResponseEntity<>(reviewCheckResponse, HttpStatus.OK);
    }

    /**
     * 리뷰 데이터 생성
     * @param reviewCreate
     */
    @PostMapping
    @UserAuth
    public void create(@RequestBody ReviewCreate reviewCreate) {
        reviewService.create(authorizationUtil.getLoginId(), reviewCreate);
    }

}
