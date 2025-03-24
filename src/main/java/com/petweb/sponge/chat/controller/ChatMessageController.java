package com.petweb.sponge.chat.controller;

import com.petweb.sponge.chat.dto.ChatMessageCreate;
import com.petweb.sponge.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/message}")
    public void sendMessage(ChatMessageCreate chatMessageCreate, SimpMessageHeaderAccessor accessor) {

    }
}
