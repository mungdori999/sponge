package com.petweb.sponge.user.domain;


import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
public class UserUpdate {

    private String name;

    private List<UserAddress> addressList;

    @Builder
    public UserUpdate(String name, List<UserAddress> addressList) {
        this.name = name;
        this.addressList = addressList;
    }
}
