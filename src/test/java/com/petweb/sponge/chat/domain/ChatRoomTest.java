package com.petweb.sponge.chat.domain;

import com.petweb.sponge.utils.TestClockHolder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ChatRoomTest {


    @Test
    public void chatRoom의_마지막채팅을_바꾼다() {

        // given
        String lastMsg = "마지막 채팅";

        ChatRoom chatRoom = ChatRoom.builder()
                .id(1L)
                .userId(1L)
                .lastChatMsg("")
                .trainerId(1L)
                .createdAt(0L)
                .modifiedAt(0L)
                .build();

        // when
        chatRoom = chatRoom.update(lastMsg, new TestClockHolder(12345L));

        // then
        assertThat(chatRoom.getLastChatMsg()).isEqualTo(lastMsg);
        assertThat(chatRoom.getModifiedAt()).isEqualTo(12345L);

    }
}
