package com.petweb.sponge.user.service;

import com.petweb.sponge.exception.error.NotFoundPost;
import com.petweb.sponge.exception.error.NotFoundUser;
import com.petweb.sponge.post.domain.post.Bookmark;
import com.petweb.sponge.post.domain.post.ProblemPost;
import com.petweb.sponge.post.dto.post.PostIdDTO;
import com.petweb.sponge.post.dto.post.ProblemPostListDTO;
import com.petweb.sponge.post.repository.post.BookmarkRepository;
import com.petweb.sponge.post.repository.post.ProblemPostRepository;
import com.petweb.sponge.user.domain.User;
import com.petweb.sponge.user.domain.UserUpdate;
import com.petweb.sponge.user.service.port.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Builder
public class UserService {

    private final UserRepository userRepository;

    private final ProblemPostRepository problemPostRepository;
    private final BookmarkRepository bookmarkRepository;
    /**
     * 유저 단건 조회
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public User getById(Long id) {
        // user,address 한번에 조회
        return userRepository.findById(id).orElseThrow(
                NotFoundUser::new);
    }

    /**
     * 유저 내정보 조회
     *
     * @param loginId
     * @return
     */
    @Transactional(readOnly = true)
    public User findMyInfo(Long loginId) {
        // user,address 한번에 조회
        return userRepository.findById(loginId).orElseThrow(
                NotFoundUser::new);
    }

    /**
     * 유저 정보 수정
     *
     * @param id
     * @param userUpdate
     * @return
     */
    @Transactional
    public User update(Long id, UserUpdate userUpdate) {
        User user =  userRepository.findById(id).orElseThrow(
                NotFoundUser::new);
        user = user.update(userUpdate);
        user = userRepository.save(user);
        return user;
    }

    /**
     * 유저 정보 삭제
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                NotFoundUser::new);
        userRepository.delete(user);
    }

    /**
     * 북마크 조회
     *
     * @param loginId
     * @return
     */
    @Transactional(readOnly = true)
    public List<ProblemPostListDTO> findBookmark(Long loginId) {
        List<ProblemPost> problemPostList = problemPostRepository.findAllPostByBookmark(loginId);
        return toPostListDto(problemPostList);

    }
    /**
     * 북마크 업데이트
     *
     * @param postIdDTO
     * @param loginId
     */
    public void updateBookmark(PostIdDTO postIdDTO, Long loginId) {
        Optional<Bookmark> bookmark = bookmarkRepository.findBookmark(postIdDTO.getProblemPostId(), loginId);
        ProblemPost problemPost = problemPostRepository.findPostWithUser(postIdDTO.getProblemPostId()).orElseThrow(
                NotFoundPost::new);;

        // 이미 북마크 되어있다면 삭제 아니라면 저장
        if (bookmark.isPresent()) {
            bookmarkRepository.delete(bookmark.get());
        } else {
            Bookmark buildBookmark = Bookmark.builder()
                    .problemPost(problemPost)
                    .userEntity(problemPost.getUserEntity())
                    .build();
            bookmarkRepository.save(buildBookmark);
        }

    }


    private List<ProblemPostListDTO> toPostListDto(List<ProblemPost> problemPosts) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return problemPosts.stream().map(problemPost ->
                ProblemPostListDTO.builder()
                        .problemPostId(problemPost.getId())
                        .title(problemPost.getTitle())
                        .content(problemPost.getContent())
                        .likeCount(problemPost.getLikeCount())
                        .createdAt(formatter.format(problemPost.getCreatedAt()))
                        .problemTypeList(problemPost.getPostCategories().stream()
                                .map(postCategory -> postCategory.getProblemType().getCode()).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());
    }


}
