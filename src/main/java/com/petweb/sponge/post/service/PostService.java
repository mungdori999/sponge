package com.petweb.sponge.post.service;

import com.petweb.sponge.exception.error.NotFoundPet;
import com.petweb.sponge.exception.error.NotFoundPost;
import com.petweb.sponge.exception.error.NotFoundUser;
import com.petweb.sponge.pet.domain.Pet;
import com.petweb.sponge.pet.service.port.PetRepository;
import com.petweb.sponge.post.controller.response.PostDetailsResponse;
import com.petweb.sponge.post.domain.Like;
import com.petweb.sponge.post.domain.post.Post;
import com.petweb.sponge.post.dto.post.PostCreate;
import com.petweb.sponge.post.dto.post.PostUpdate;
import com.petweb.sponge.post.repository.LikeRepository;
import com.petweb.sponge.post.repository.post.*;
import com.petweb.sponge.post.repository.ProblemTypeRepository;
import com.petweb.sponge.user.domain.User;
import com.petweb.sponge.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final ProblemTypeRepository problemTypeRepository;
    private final PostFileRepository postFileRepository;


    /**
     * 글 단건 조회
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public PostDetailsResponse findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                NotFoundPost::new);
        Pet pet = petRepository.findById(post.getPetId()).orElseThrow(
                NotFoundPet::new);
        return PostDetailsResponse.from(post, pet);
    }

    @Transactional(readOnly = true)
    public List<Post> findMyInfo(Long loginId, int page) {
        return postRepository.findListByUserId(loginId, page);

    }

    /**
     * 카테고리별 글 전체 조회
     *
     * @param categoryCode
     * @return
     */
    @Transactional(readOnly = true)
    public List<Post> findPostList(Long categoryCode, int page) {
        return postRepository.findListByCode(categoryCode, page);
    }

    /**
     * 검색
     *
     * @param keyword
     * @param page
     * @return
     */
    @Transactional(readOnly = true)
    public List<Post> search(String keyword, int page) {
        return postRepository.findByKeyword(keyword, page);
    }

    /**
     * 글 작성 저장
     *
     * @param loginId
     * @param postCreate
     * @return
     */
    @Transactional
    public PostDetailsResponse create(Long loginId, PostCreate postCreate) {
        //현재 로그인 유저 정보 가져오기
        User user = userRepository.findById(loginId).orElseThrow(
                NotFoundUser::new);
        //선택한 반려동물 정보 가져오기
        Pet pet = petRepository.findById(postCreate.getPetId()).orElseThrow(
                NotFoundPet::new);
        Post post = Post.from(user.getId(), pet.getId(), postCreate);
        post = postRepository.save(post);
        return PostDetailsResponse.from(post, pet);
    }

    /**
     * 글 수정
     *
     * @param loginId
     * @param id
     * @param postUpdate
     * @return
     */
    @Transactional
    public PostDetailsResponse update(Long loginId, Long id, PostUpdate postUpdate) {
        Post post = postRepository.findById(id).orElseThrow(
                NotFoundPost::new);
        Pet pet = petRepository.findById(post.getPetId()).orElseThrow(
                NotFoundPet::new);
        post.checkUser(loginId);
        postRepository.initPost(post.getId());
        post = post.update(postUpdate);
        post = postRepository.save(post);
        return PostDetailsResponse.from(post, pet);
    }


    /**
     * 글 삭제
     *
     * @param loginId
     * @param id
     */
    @Transactional
    public void delete(Long loginId, Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                NotFoundPost::new);
        post.checkUser(loginId);
        postRepository.delete(post);
    }

    /**
     * 문제행동글 관련 파일 삭제
     *
     * @param loginId
     * @param problemPostId
     * @param fileUrlList
     */
    @Transactional
    public void deletePostFiles(Long loginId, Long problemPostId, List<String> fileUrlList) {
//        PostEntity postEntity = postRepository.findById(problemPostId).orElseThrow(NotFoundPost::new);
//        if (!postEntity.getUserEntity().getId().equals(loginId)) {
//            throw new LoginIdError();
//        }
//        postFileRepository.deleteByFiles(fileUrlList);
    }

    /**
     * 추천수 업데이트
     *
     * @param loginId
     * @param postId
     */
    public void updateLikeCount(Long loginId, Long postId) {
        Optional<Like> like = likeRepository.findLike(postId, loginId);
        Post post = postRepository.findById(postId).orElseThrow(
                NotFoundPost::new);
        /**
         * 추천이 이미 있다면 추천을 삭제 추천수 -1
         * 추천이 없다면 추천을 저장 추천수 +1
         */
        if (like.isPresent()) {
            post.decreaseLikeCount();
            likeRepository.delete(like.get());
            postRepository.save(post);
        } else {
            Like newLike = Like.from(postId, loginId);
            post.increaseLikeCount();
            likeRepository.save(newLike);
        }
    }
}
