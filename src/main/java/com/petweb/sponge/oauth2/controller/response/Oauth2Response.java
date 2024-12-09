package com.petweb.sponge.oauth2.controller.response;

import com.petweb.sponge.trainer.domain.Trainer;
import com.petweb.sponge.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Oauth2Response {

    private Long id;
    private String name;
    private String refreshToken;

    public static Oauth2Response userFrom(User user, String refreshToken) {
        return Oauth2Response.builder()
                .id(user.getId())
                .name(user.getName())
                .refreshToken(refreshToken)
                .build();
    }

    public static Oauth2Response trainerFrom(Trainer trainer, String refreshToken) {
        return Oauth2Response.builder()
                .id(trainer.getId())
                .name(trainer.getName())
                .refreshToken(refreshToken)
                .build();
    }
}
