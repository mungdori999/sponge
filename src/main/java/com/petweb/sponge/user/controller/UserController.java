package com.petweb.sponge.user.controller;

import com.petweb.sponge.auth.UserAuth;
import com.petweb.sponge.exception.error.LoginIdError;
import com.petweb.sponge.user.controller.response.UserResponse;
import com.petweb.sponge.user.domain.User;
import com.petweb.sponge.user.dto.UserUpdate;
import com.petweb.sponge.user.service.UserService;
import com.petweb.sponge.utils.AuthorizationUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final AuthorizationUtil authorizationUtil;

    /**
     * 유저 단건조회
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(UserResponse.from(user), HttpStatus.OK);
    }

    /**
     * 자신의 계정정보를 불러옴
     *
     * @return
     */
    @GetMapping("/my_info")
    @UserAuth
    public ResponseEntity<UserResponse> getMyInfo() {
        User user = userService.findMyInfo(authorizationUtil.getLoginId());
        return new ResponseEntity<>(UserResponse.from(user), HttpStatus.OK);
    }


    /**
     * 유저 정보 수정
     *
     * @param id
     * @return
     */
    @PatchMapping("/{id}")
    @UserAuth
    public ResponseEntity<UserResponse> update(@PathVariable("id") Long id, @RequestBody UserUpdate userUpdate) {
        if (authorizationUtil.getLoginId().equals(id)) {
            User user = userService.update(id, userUpdate);
            return new ResponseEntity<>(UserResponse.from(user),HttpStatus.OK);
        } else {
            throw new LoginIdError();
        }
    }

    /**
     * 회원탈퇴
     *
     * @param id
     * @param response
     */
    @DeleteMapping("/{id}")
    @UserAuth
    public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
        if (authorizationUtil.getLoginId().equals(id)) {
            userService.delete(id);
        } else {
            throw new LoginIdError();
        }
        Cookie cookie = new Cookie("Authorization", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setStatus(200);
    }

//    @GetMapping("/bookmark")
//    @UserAuth
//    public ResponseEntity<List<ProblemPostListDTO>> getBookmark() {
//        List<ProblemPostListDTO> bookmarkList = userService.findBookmark(authorizationUtil.getLoginId());
//        return new ResponseEntity<>(bookmarkList,HttpStatus.OK);
//    }
    /**
     * 글 북마크 업데이트
     *
     * @param postIdDTO
     */
//    @PostMapping("/bookmark")
//    @UserAuth
//    public void updateBookmark(@RequestBody PostIdDTO postIdDTO) {
//        userService.updateBookmark(postIdDTO, authorizationUtil.getLoginId());
//    }

}
