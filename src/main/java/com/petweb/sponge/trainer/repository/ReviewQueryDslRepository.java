package com.petweb.sponge.trainer.repository;



import java.util.List;

public interface ReviewQueryDslRepository {

    List<ReviewEntity> findByTrainerId(Long trainerId, int page);
}
