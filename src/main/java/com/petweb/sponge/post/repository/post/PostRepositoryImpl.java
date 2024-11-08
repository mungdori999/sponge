package com.petweb.sponge.post.repository.post;

import com.petweb.sponge.post.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final PostJpaRepository postJpaRepository;

    @Override
    public Optional<Post> findById(Long id) {
        return postJpaRepository.findPostById(id).map(PostEntity::toModel);
    }

    @Override
    public List<Post> findListByCode(Long problemTypeCode, int page) {
        return postJpaRepository.findListByCode(problemTypeCode, page).stream().map(PostEntity::toModel).collect(Collectors.toList());
    }
}
