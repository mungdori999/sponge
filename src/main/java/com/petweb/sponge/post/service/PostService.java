package com.petweb.sponge.post.service;

import com.petweb.sponge.exception.error.NotFoundPet;
import com.petweb.sponge.exception.error.NotFoundPost;
import com.petweb.sponge.exception.error.NotFoundUser;
import com.petweb.sponge.pet.domain.Pet;
import com.petweb.sponge.pet.service.port.PetRepository;
import com.petweb.sponge.post.controller.response.PostDetailsResponse;
import com.petweb.sponge.post.domain.post.Post;
import com.petweb.sponge.post.dto.post.PostCreate;
import com.petweb.sponge.post.dto.post.ProblemPostListDTO;
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
    private final ProblemTypeRepository problemTypeRepository;
    private final PostRecommendRepository postRecommendRepository;
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

    /**
     * 카테고리별 글 전체 조회
     *
     * @param problemTypeCode
     * @return
     */
    @Transactional(readOnly = true)
    public List<Post> findPostList(Long problemTypeCode, int page) {
        List<Post> postList = postRepository.findListByCode(problemTypeCode, page);
        return postList;
    }

    /**
     * 검색
     *
     * @param keyword
     * @param page
     * @return
     */
    @Transactional(readOnly = true)
    public List<ProblemPostListDTO> searchPost(String keyword, int page) {
//        List<PostEntity> postEntities = postRepository.searchPostByKeyword(keyword, page);
//        return toPostListDto(postEntities);
        return null;
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

//        //선택한 반려동물 정보 가져오기
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
     * @param problemPostId
     * @param problemPostDTO
     */
    @Transactional
    public void update(Long loginId, Long problemPostId, PostCreate problemPostDTO) {
//        PostEntity postEntity = postRepository.findById(problemPostId).orElseThrow(
//                NotFoundPost::new);
//        // 글을 쓴 유저가 아닌경우
//        if (!postEntity.getUserEntity().getId().equals(loginId)) {
//            throw new LoginIdError();
//        }
//        //글 초기화
//        postRepository.initProblemPost(problemPostId);
//
//        postEntity.updatePost(problemPostDTO.getTitle()
//                , problemPostDTO.getContent()
//                , problemPostDTO.getFileUrlList()
//                , problemPostDTO.getHasTagList());
//
//        //ProblemType 조회해서 -> PostCategory로 변환 저장
//        problemTypeRepository.findAllByCodeIn(problemPostDTO.getProblemTypeList())
//                .stream().map(problemType -> PostCategoryEntity.builder()
//                        .problemPost(postEntity)
//                        .problemType(problemType)
//                        .build()).collect(Collectors.toList())
//                .forEach(postCategory -> postEntity.getPostCategories().add(postCategory));
    }


    /**
     * 글 삭제
     *
     * @param loginId
     * @param problemPostId
     */
    @Transactional
    public void deletePost(Long loginId, Long problemPostId) {
//        PostEntity postEntity = postRepository.findById(problemPostId).orElseThrow(
//                NotFoundPost::new);
//        ;
//        // 글을 쓴 유저가 아닌경우
//        if (!postEntity.getUserEntity().getId().equals(loginId)) {
//            throw new LoginIdError();
//        }
//        postRepository.deletePost(problemPostId);
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
     * @param problemPostId
     * @param loginId
     */
    public void updateLikeCount(Long problemPostId, Long loginId) {
        Optional<LikeEntity> recommend = postRecommendRepository.findRecommend(problemPostId, loginId);
//        PostEntity postEntity = postRepository.findPostWithUser(problemPostId).orElseThrow(
//                NotFoundPost::new);
//        ;
//        /**
//         * 추천이 이미 있다면 추천을 삭제 추천수 -1
//         * 추천이 없다면 추천을 저장 추천수 +1
//         */
//        if (recommend.isPresent()) {
//            postEntity.decreaseLikeCount();
//            postRecommendRepository.delete(recommend.get());
//        } else {
//            LikeEntity likeEntity = LikeEntity.builder()
//                    .problemPost(postEntity)
//                    .userEntity(postEntity.getUserEntity())
//                    .build();
//            postEntity.increaseLikeCount();
//            postRecommendRepository.save(likeEntity);
//        }
    }


}
