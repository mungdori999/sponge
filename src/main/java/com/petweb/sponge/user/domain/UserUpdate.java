package com.petweb.sponge.user.domain;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserUpdate {

    private String name;
    private String address;

}
