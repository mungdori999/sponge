package com.petweb.sponge.post.controller;

import com.petweb.sponge.auth.UserAuth;
import com.petweb.sponge.post.controller.response.PostDetailsResponse;
import com.petweb.sponge.post.controller.response.PostListResponse;
import com.petweb.sponge.post.domain.post.Post;
import com.petweb.sponge.post.dto.post.PostIdDTO;
import com.petweb.sponge.post.dto.post.PostCreate;
import com.petweb.sponge.post.dto.post.PostUpdate;
import com.petweb.sponge.post.dto.post.ProblemPostListDTO;
import com.petweb.sponge.post.service.PostService;
import com.petweb.sponge.utils.AuthorizationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {


    private final PostService postService;
    private final AuthorizationUtil authorizationUtil;

    /**
     * 글 단건 조회
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostDetailsResponse> getById(@PathVariable("id") Long id) {
        PostDetailsResponse post = postService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    /**
     * 카테고리별 글 전체조회
     *
     * @param problemTypeCode
     * @return
     */
    @GetMapping
    public ResponseEntity<List<PostListResponse>> getAllPost(@RequestParam("problemTypeCode") Long problemTypeCode, @RequestParam("page") int page) {
        List<Post> postList = postService.findPostList(problemTypeCode, page);
        return new ResponseEntity<>(postList.stream().map(PostListResponse::from).collect(Collectors.toList()), HttpStatus.OK);
    }

    /**
     * 검색 기능
     *
     * @param keyword
     * @param page
     * @return
     */
//    @GetMapping("/search")
//    public ResponseEntity<List<ProblemPostListDTO>> searchPost(@RequestParam("keyword") String keyword, @RequestParam("page") int page) {
//        List<ProblemPostListDTO> problemPostList = postService.searchPost(keyword, page);
//        return new ResponseEntity<>(problemPostList, HttpStatus.OK);
//    }

    /**
     * 글 작성 저장
     *
     * @param postCreate
     * @return
     */
    @PostMapping
    @UserAuth
    public ResponseEntity<PostDetailsResponse> create(@RequestBody PostCreate postCreate) {
        PostDetailsResponse post = postService.create(authorizationUtil.getLoginId(), postCreate);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    /**
     * 글 수정
     *
     * @param id
     * @param postUpdate
     * @return
     */
    @PatchMapping("/{id}")
    @UserAuth
    public ResponseEntity<PostDetailsResponse> update(@PathVariable("id") Long id, @RequestBody PostUpdate postUpdate) {
        PostDetailsResponse post = postService.update(authorizationUtil.getLoginId(), id, postUpdate);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    /**
     * 글 삭제
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    @UserAuth
    public void delete(@PathVariable("id") Long id) {
        postService.delete(authorizationUtil.getLoginId(), id);
    }

    /**
     * 추천수 업데이트
     *
     * @param postIdDto
     */
//    @PostMapping("/like")
//    @UserAuth
//    public void updateLikeCount(@RequestBody PostIdDTO postIdDto) {
//        postService.updateLikeCount(postIdDto.getProblemPostId(), authorizationUtil.getLoginId());
//    }


}