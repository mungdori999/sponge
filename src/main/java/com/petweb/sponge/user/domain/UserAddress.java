package com.petweb.sponge.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserAddress {

    private Long id;
    private String city;
    private String town;
    private User user;

    @Builder
    public UserAddress(Long id, String city, String town, User user) {
        this.id = id;
        this.city = city;
        this.town = town;
        this.user = user;
    }
}
