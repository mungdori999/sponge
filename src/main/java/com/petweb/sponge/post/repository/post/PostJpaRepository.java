package com.petweb.sponge.post.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<PostEntity,Long>, PostQueryDslRepository {


    @Query("SELECT p FROM PostEntity p WHERE p.id IN :postIdList")
    List<PostEntity> findListByPostListId(List<Long> postIdList);
}
