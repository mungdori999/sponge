package com.petweb.sponge.oauth2.controller.response;

import com.petweb.sponge.user.controller.response.UserResponse;
import com.petweb.sponge.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserOauth2Response {

    private Long id;
    private String name;

    public static UserOauth2Response from(User user) {
        return UserOauth2Response.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
