package com.petweb.sponge.oauth2.controller.response;

import com.petweb.sponge.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Builder
public class UserOauth2Response {

    private Long id;
    private String name;
    private String refreshToken;
    @Value("${spring.jwt.refresh-expire-length}")
    private long refreshExpireTime;

    public static UserOauth2Response from(User user,String refreshToken) {
        return UserOauth2Response.builder()
                .id(user.getId())
                .name(user.getName())
                .refreshToken(refreshToken)
                .build();
    }
}
