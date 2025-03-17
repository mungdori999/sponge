package com.petweb.sponge.post.mock.answer;

import com.petweb.sponge.post.domain.answer.AnswerLike;
import com.petweb.sponge.post.repository.answer.AnswerLikeRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class MockAnswerLikeRepository implements AnswerLikeRepository {

    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private final List<AnswerLike> data = new CopyOnWriteArrayList<>();

    @Override
    public Optional<AnswerLike> findByAnswerId(Long answerId, Long loginId) {
        return data.stream()
                .filter(like -> like.getAnswerId().equals(answerId) && like.getUserId().equals(loginId))
                .findFirst();
    }

    @Override
    public void save(AnswerLike answerLike) {
        if (answerLike.getAnswerId() == null || answerLike.getAnswerId() == 0L) {
            AnswerLike newAnswerLike = AnswerLike.builder()
                    .id(autoGeneratedId.incrementAndGet())
                    .answerId(answerLike.getAnswerId())
                    .userId(answerLike.getUserId())
                    .build();
            data.add(newAnswerLike);
        } else {
            data.removeIf(item -> Objects.equals(item.getId(), answerLike.getId()));
            data.add(answerLike);
        }
    }

    @Override
    public void delete(AnswerLike answerLike) {
        data.remove(answerLike);
    }

    @Override
    public void deleteByAnswerId(Long answerId) {
        data.removeIf(answerLike -> answerLike.getAnswerId().equals(answerId));
    }
}
