package com.petweb.sponge.user.domain;


import lombok.Builder;
import lombok.Getter;

@Getter
public class UserUpdate {

    private String name;
    private String address;

    @Builder

    public UserUpdate(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
