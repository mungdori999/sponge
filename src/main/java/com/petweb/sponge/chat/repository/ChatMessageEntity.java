package com.petweb.sponge.chat.repository;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "chat_message")
public class ChatMessageEntity {

    @Id
    private String id;
    private String message;
    private Long userId;
    private Long trainerId;
    private Long createdAt;
}
