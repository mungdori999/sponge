package com.petweb.sponge.post.service;

import com.petweb.sponge.pet.mock.MockPetRepository;
import com.petweb.sponge.pet.service.port.PetRepository;
import com.petweb.sponge.post.domain.post.*;
import com.petweb.sponge.post.mock.MockPostRepository;
import com.petweb.sponge.post.repository.post.PostRepository;
import com.petweb.sponge.user.mock.MockUserRepository;
import com.petweb.sponge.user.service.port.UserRepository;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Timestamp;
import java.util.List;

class PostServiceTest {

    private PostService postService;

    @BeforeEach()
    void init() {
        PetRepository petRepository = new MockPetRepository();
        UserRepository userRepository = new MockUserRepository();
        PostRepository postRepository = new MockPostRepository();
        PostService.builder()
                .petRepository(petRepository)
                .userRepository(userRepository)
                .postRepository(postRepository).build();

        Post post = Post.builder()
                .postContent(PostContent.builder()
                        .title("테스트 제목")
                        .content("테스트 게시글 내용")
                        .duration("한달이상")
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .modifiedAt(new Timestamp(System.currentTimeMillis()))
                        .build())
                .userId(1L)
                .petId(1L)
                .postFileList(List.of(
                        PostFile.builder().fileUrl("").build(),
                        PostFile.builder().fileUrl("").build()
                ))
                .tagList(List.of(
                        Tag.builder().hashtag("짖음").build(),
                        Tag.builder().hashtag("건강").build()
                ))
                .postCategoryList(List.of(
                        PostCategory.builder().categoryCode(100L).build(),
                        PostCategory.builder().categoryCode(200L).build()
                ))
                .build();
        postRepository.save(post);

    }


}