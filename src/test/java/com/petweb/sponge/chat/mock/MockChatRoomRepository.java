package com.petweb.sponge.chat.mock;

import com.petweb.sponge.chat.domain.ChatRoom;
import com.petweb.sponge.chat.repository.ChatRoomRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class MockChatRoomRepository implements ChatRoomRepository {

    private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private final List<ChatRoom> data = new CopyOnWriteArrayList<>();
    private static final int PAGE_SIZE = 10;
    @Override
    public Optional<ChatRoom> findById(Long id) {
        return data.stream()
                .filter(chatRoom -> chatRoom.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<ChatRoom> findListByTrainerId(Long loginId, int page) {
        int offset = page * PAGE_SIZE;
        return data.stream()
                .filter(chatRoom -> chatRoom.getTrainerId().equals(loginId))
                .skip(offset) // 페이지네이션: 한 페이지당 10개 기준
                .limit(PAGE_SIZE)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatRoom> findListByUserId(Long loginId, int page) {
        int offset = page * PAGE_SIZE;
        return data.stream()
                .filter(chatRoom -> chatRoom.getUserId().equals(loginId))
                .skip(offset) // 페이지네이션: 한 페이지당 10개 기준
                .limit(PAGE_SIZE)
                .collect(Collectors.toList());
    }

    @Override
    public ChatRoom save(ChatRoom chatRoom) {
        if (chatRoom.getId() == null || chatRoom.getId() == 0L) {
            ChatRoom newChatRoom = ChatRoom.builder()
                    .id(autoGeneratedId.incrementAndGet())
                    .userId(chatRoom.getUserId())
                    .lastChatMsg(chatRoom.getLastChatMsg())
                    .trainerId(chatRoom.getTrainerId())
                    .createdAt(chatRoom.getCreatedAt())
                    .modifiedAt(chatRoom.getModifiedAt())
                    .build();
            data.add(newChatRoom);
            return newChatRoom;
        } else {
            data.removeIf(existing -> existing.getId().equals(chatRoom.getId()));
            data.add(chatRoom);
            return chatRoom;
        }
    }

}
