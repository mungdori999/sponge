package com.petweb.sponge.user.domain;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class User {

    private Long id;
    private String email; //로그인 아이디
    private String name; //이름
    private String address; // 주소
    private Timestamp createdAt;
    private Timestamp modifiedAt;

    @Builder
    public User(Long id, String email, String name, String address, Timestamp createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }


    public User update(UserUpdate userUpdate) {
        return User.builder()
                .id(id)
                .email(email)
                .name(userUpdate.getName())
                .address(userUpdate.getAddress())
                .createdAt(createdAt).build();
    }
}
