package com.petweb.sponge.chat.controller;

import com.petweb.sponge.auth.UserAuth;
import com.petweb.sponge.chat.dto.ChatRoomCreate;
import com.petweb.sponge.chat.service.ChatRoomService;
import com.petweb.sponge.utils.AuthorizationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatRoom")
@Slf4j
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final AuthorizationUtil authorizationUtil;

    @PostMapping("/personal") //개인 DM 채팅방 생성
    @UserAuth
    public void createPersonalChatRoom(@RequestBody ChatRoomCreate chatRoomCreate) {
        chatRoomService.create(authorizationUtil.getLoginId(), chatRoomCreate);
    }
}
