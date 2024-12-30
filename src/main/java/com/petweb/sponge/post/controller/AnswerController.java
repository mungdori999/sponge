package com.petweb.sponge.post.controller;

import com.petweb.sponge.auth.TrainerAuth;
import com.petweb.sponge.auth.UserAuth;
import com.petweb.sponge.post.controller.response.answer.AnswerListResponse;
import com.petweb.sponge.post.dto.answer.*;
import com.petweb.sponge.post.service.AnswerService;
import com.petweb.sponge.utils.AuthorizationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answer")
public class AnswerController {

    private final AnswerService answerService;
    private final AuthorizationUtil authorizationUtil;


    /**
     * 문제행동글에 딸린 훈련사 답변 조회
     *
     * @param postId
     * @return
     */
    @GetMapping
    public ResponseEntity<List<AnswerListResponse>> getAllAnswer(@RequestParam Long postId) {
        List<AnswerListResponse> answerList = answerService.findAnswerList(postId);
        return new ResponseEntity<>(answerList, HttpStatus.OK);
    }

    /**
     * 훈련사 답변 작성
     *
     * @param answerCreate
     */
    @PostMapping
    @TrainerAuth
    public void create(@RequestBody AnswerCreate answerCreate) {
        answerService.create(authorizationUtil.getLoginId(), answerCreate);
    }

    /**
     * 훈련사 답변 수정
     *
     * @param id
     * @param answerUpdate
     */
    @PatchMapping("/{id}")
    @TrainerAuth
    public void update(@PathVariable Long id, @RequestBody AnswerUpdate answerUpdate) {
        answerService.update(id, answerUpdate, authorizationUtil.getLoginId());
    }

    /**
     * 훈련사 답변 삭제
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    @TrainerAuth
    public void delete(@PathVariable Long id) {
        answerService.delete(authorizationUtil.getLoginId(), id);
    }

    /**
     * 훈련사 답변 채택
     *
     * @param adoptAnswerDTO
     */
    @PostMapping("/adopt")
    @UserAuth
    public void registerAdoptAnswer(@RequestBody AdoptAnswerDTO adoptAnswerDTO) {
        answerService.saveAdoptAnswer(adoptAnswerDTO, authorizationUtil.getLoginId());
    }

    /**
     * 훈련사 답변 추천
     * @param answerId
     */
    @PostMapping("/like")
    @UserAuth
    public void updateLikeC(@RequestParam("answerId")Long answerId) {
        answerService.updateLike(authorizationUtil.getLoginId(), answerId);

    }


}
