package com.petweb.sponge.post.repository.post;

import com.petweb.sponge.post.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository  {

     Optional<Post> findById(Long id);

    List<Post> findListByCode(Long problemTypeCode, int page);

    Post save(Post post);
}
