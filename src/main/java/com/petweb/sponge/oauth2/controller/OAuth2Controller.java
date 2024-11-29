package com.petweb.sponge.oauth2.controller;

import com.petweb.sponge.exception.error.NotFoundToken;
import com.petweb.sponge.jwt.JwtUtil;
import com.petweb.sponge.jwt.Token;
import com.petweb.sponge.oauth2.controller.response.UserOauth2Response;
import com.petweb.sponge.oauth2.dto.LoginRequest;
import com.petweb.sponge.oauth2.service.KaKaoService;
import com.petweb.sponge.oauth2.service.OAuth2Service;
import com.petweb.sponge.oauth2.service.UserOAuth2;
import com.petweb.sponge.user.domain.User;
import com.petweb.sponge.utils.LoginType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;
    private final KaKaoService kaKaoService;
    private final JwtUtil jwtUtil;


    @PostMapping("/kakao")
    public ResponseEntity<UserOauth2Response> authKaKao(@RequestBody LoginRequest loginRequest) {
        if (Objects.equals(loginRequest.getLoginType(), LoginType.USER.getLoginType())) {
            UserOAuth2 userOAuth2 = kaKaoService.getKaKaoInfo(loginRequest.getAccessToken());
            if (userOAuth2 != null) {
                User user = oAuth2Service.loadUser(userOAuth2);
                Token token = jwtUtil.createToken(user.getId(), user.getName(), loginRequest.getLoginType());
                return ResponseEntity.ok().header("Authorization",token.getAccessToken())
                        .body(UserOauth2Response.from(user,token.getRefreshToken()));
            }
            else {
                throw new NotFoundToken();
            }
        }
        return null;
    }
}
