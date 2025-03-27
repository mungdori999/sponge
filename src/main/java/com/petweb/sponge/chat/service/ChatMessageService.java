package com.petweb.sponge.chat.service;

import com.petweb.sponge.chat.domain.ChatMessage;
import com.petweb.sponge.chat.dto.ChatMessageCreate;
import com.petweb.sponge.chat.repository.ChatMessageRepository;
import com.petweb.sponge.utils.ClockHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ClockHolder clockHolder;
    private final ChatMessageRepository chatMessageRepository;

    /**
     * 지금까지 메세지 가져오기
     * @param chatRoomId
     * @param page
     * @return
     */
    @Transactional(readOnly = true)
    public List<ChatMessage> findListByChatRoomId(Long chatRoomId, int page) {
        return chatMessageRepository.findListByChatRoomId(chatRoomId,page);
    }

    /**
     * 메세저 저장
     * @param chatMessageCreate
     * @param pubId
     * @param loginType
     * @return
     */
    @Transactional
    public ChatMessage create(ChatMessageCreate chatMessageCreate, Long pubId, String loginType) {
        ChatMessage chatMessage = ChatMessage.from(chatMessageCreate, pubId, loginType, clockHolder);

        return chatMessageRepository.save(chatMessage);
    }
}
