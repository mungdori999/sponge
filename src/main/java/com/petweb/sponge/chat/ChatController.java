package com.petweb.sponge.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/room/{roomId}")
    public void sendMessage(@Payload ChatMessage chatMessage, @DestinationVariable String roomId) {
        messagingTemplate.convertAndSend("/api/topic/room/" + roomId, chatMessage);
    }
}
