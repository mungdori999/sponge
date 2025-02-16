package com.petweb.sponge.post.service;

import com.petweb.sponge.pet.mock.MockPetRepository;
import com.petweb.sponge.pet.service.port.PetRepository;
import com.petweb.sponge.post.mock.MockPostRepository;
import com.petweb.sponge.post.repository.post.BookmarkRepository;
import com.petweb.sponge.post.repository.post.PostLikeRepository;
import com.petweb.sponge.post.repository.post.PostRepository;
import com.petweb.sponge.user.mock.MockUserRepository;
import com.petweb.sponge.user.service.port.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

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


    }



}