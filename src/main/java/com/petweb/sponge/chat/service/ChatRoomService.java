package com.petweb.sponge.chat.service;

import com.petweb.sponge.chat.domain.ChatRoom;
import com.petweb.sponge.chat.dto.ChatRoomCreate;
import com.petweb.sponge.chat.repository.ChatRoomRepository;
import com.petweb.sponge.utils.ClockHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ClockHolder clockHolder;

    public ChatRoom create(Long loginId, ChatRoomCreate chatRoomCreate) {
        ChatRoom chatRoom = ChatRoom.from(chatRoomCreate, loginId, clockHolder);
        return chatRoomRepository.save(chatRoom);
    }
}
