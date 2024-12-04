package com.petweb.sponge.post.repository;

import com.petweb.sponge.post.domain.Like;
import com.petweb.sponge.post.repository.post.LikeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository{

    private final LikeJpaRepository likeJpaRepository;
    @Override
    public Optional<Like> findLike(Long postId, Long loginId) {
        return likeJpaRepository.findLike(postId, loginId)
                .map(LikeEntity::toModel);
    }

    @Override
    public void save(Like like) {
        likeJpaRepository.save(LikeEntity.from(like));
    }

    @Override
    public void delete(Like like) {
        likeJpaRepository.delete(LikeEntity.from(like));
    }
}
