package com.petweb.sponge.user.domain;


import lombok.Getter;

import java.util.List;
@Getter
public class UserUpdate {

    private String name;

    private List<UserAddress> addressList;
}
