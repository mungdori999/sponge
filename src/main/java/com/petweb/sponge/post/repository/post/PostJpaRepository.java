package com.petweb.sponge.post.repository.post;

import com.petweb.sponge.post.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<PostEntity,Long>, PostQueryDslRepository {

}
