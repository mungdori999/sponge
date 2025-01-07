package com.petweb.sponge.trainer.controller;

import com.petweb.sponge.auth.UserAuth;
import com.petweb.sponge.trainer.dto.ReviewCreate;
import com.petweb.sponge.trainer.service.ReviewService;
import com.petweb.sponge.utils.AuthorizationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final AuthorizationUtil authorizationUtil;

    @PostMapping
    @UserAuth
    public void create(@RequestBody ReviewCreate reviewCreate) {
        reviewService.create(authorizationUtil.getLoginId(), reviewCreate);
    }

}
