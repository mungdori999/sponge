package com.petweb.sponge.user.domain;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
public class User {

    private Long id;
    private String email; //로그인 아이디
    private String name; //이름

    private List<UserAddress> userAddressList = new ArrayList<>();

    private Timestamp createdAt;
    private Timestamp modifiedAt;

    @Builder
    public User(Long id, String email, String name, List<UserAddress> userAddressList, Timestamp createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.userAddressList = userAddressList;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public User update(UserUpdate userUpdate) {
        return User.builder()
                .id(id)
                .email(email)
                .name(userUpdate.getName())
                .userAddressList(userUpdate.getAddressList())
                .createdAt(createdAt).build();
    }
}
