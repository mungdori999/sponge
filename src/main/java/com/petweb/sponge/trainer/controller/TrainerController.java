package com.petweb.sponge.trainer.controller;

import com.petweb.sponge.auth.TrainerAuth;
import com.petweb.sponge.auth.UserAuth;
import com.petweb.sponge.exception.error.LoginIdError;
import com.petweb.sponge.trainer.controller.response.TrainerResponse;
import com.petweb.sponge.trainer.domain.Trainer;
import com.petweb.sponge.trainer.dto.ReviewDTO;
import com.petweb.sponge.trainer.dto.ReviewDetailDTO;
import com.petweb.sponge.trainer.dto.TrainerDetailDTO;
import com.petweb.sponge.trainer.service.TrainerService;
import com.petweb.sponge.utils.AuthorizationUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trainer")
public class TrainerController {

    private final TrainerService trainerService;
    private final AuthorizationUtil authorizationUtil;

    /**
     * 훈련사 단건조회
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<TrainerResponse> getById(@PathVariable("id") Long id) {
        Trainer trainer = trainerService.getById(id);
        return new ResponseEntity<>(TrainerResponse.from(trainer), HttpStatus.OK);
    }

    /**
     * 자신의 계정정보를 가져옴
     * @return
     */
    @GetMapping("/my_info")
    @TrainerAuth
    public ResponseEntity<TrainerResponse> getMyInfo() {
        Trainer trainer = trainerService.findMyInfo(authorizationUtil.getLoginId());
        return new ResponseEntity<>(TrainerResponse.from(trainer), HttpStatus.OK);
    }

    /**
     * 훈련사 정보 저장
     * @param trainerDetailDTO
     * @return
     */
    @PostMapping()
    public void create(@RequestBody TrainerDetailDTO trainerDetailDTO) {
        trainerService.saveTrainer(trainerDetailDTO);
    }

    /**
     * 훈련사 정보 수정
     * @param trainerId
     * @param trainerDetailDTO
     */
    @PatchMapping("/{trainerId}")
    @TrainerAuth
    public void updateTrainer(@PathVariable("trainerId")Long trainerId,@RequestBody TrainerDetailDTO trainerDetailDTO) {
        if (authorizationUtil.getLoginId().equals(trainerId)) {
            trainerService.updateTrainer(trainerId, trainerDetailDTO);
        }
        else {
            throw new LoginIdError();
        }
    }

    /**
     * 회원탈퇴
     * @param trainerId
     */
    @DeleteMapping("/{trainerId}")
    @TrainerAuth
    public void removeTrainer(@PathVariable("trainerId") Long trainerId, HttpServletResponse response) {
        if (authorizationUtil.getLoginId().equals(trainerId)) {
        trainerService.deleteTrainer(trainerId);
        }
        else {
            throw new LoginIdError();
        }

        //로그인 쿠키 삭제
        Cookie cookie = new Cookie("Authorization", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setStatus(200);
    }

    /**
     * 해당하는 훈련사의 리뷰데이터 조회
     * @param trainerId
     * @return
     */
    @GetMapping("/review/{trainerId}")
    public ResponseEntity<List<ReviewDetailDTO>> getReviews(@PathVariable("trainerId") Long trainerId) {
        List<ReviewDetailDTO> reviewList = trainerService.findAllReview(trainerId);
        return new ResponseEntity<>(reviewList,HttpStatus.OK);
    }
    /**
     * 리뷰 쓰기
     * TODO 훈련사 답변이달려야만 리뷰를 쓸 수 있음
     * @param reviewDTO
     */
    @PostMapping("/review")
    @UserAuth
    public void writeReview(@RequestBody ReviewDTO reviewDTO) {
        trainerService.saveReview(reviewDTO,authorizationUtil.getLoginId());
    }
}
