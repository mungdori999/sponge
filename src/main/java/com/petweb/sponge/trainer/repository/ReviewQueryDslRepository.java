package com.petweb.sponge.trainer.repository;


import com.petweb.sponge.trainer.controller.response.ReviewResponse;

import java.util.List;

public interface ReviewQueryDslRepository {

    List<ReviewResponse> findByTrainerId(Long trainerId, int page);
}
