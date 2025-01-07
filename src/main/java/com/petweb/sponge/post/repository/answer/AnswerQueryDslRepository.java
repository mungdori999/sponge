package com.petweb.sponge.post.repository.answer;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AnswerQueryDslRepository {


    List<AnswerEntity> findListByTrainerId(Long trainerId, int page);
}
