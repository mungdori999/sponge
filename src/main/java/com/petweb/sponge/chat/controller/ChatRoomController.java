package com.petweb.sponge.chat.controller;

import com.petweb.sponge.auth.UserAuth;
import com.petweb.sponge.chat.dto.ChatRoomCreate;
import com.petweb.sponge.chat.service.ChatRoomService;
import com.petweb.sponge.utils.AuthorizationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatRoom")
@Slf4j
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final AuthorizationUtil authorizationUtil;

    @GetMapping("/my_info")
    public void getMyChatRoom(@RequestParam("page")  int page) {
        chatRoomService.findMyInfo(authorizationUtil.getLoginId(),authorizationUtil.getLoginType(),page);
    }

    /**
     * 개인 DM 채팅방 생성
     * @param chatRoomCreate
     */
    @PostMapping("/personal")
    @UserAuth
    public void createPersonalChatRoom(@RequestBody ChatRoomCreate chatRoomCreate) {
        chatRoomService.create(authorizationUtil.getLoginId(), chatRoomCreate);
    }
}
