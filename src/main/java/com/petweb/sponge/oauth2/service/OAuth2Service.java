package com.petweb.sponge.oauth2.service;

import com.petweb.sponge.user.domain.User;
import com.petweb.sponge.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2Service {

    private final UserRepository userRepository;
    public User loadUser(UserOAuth2 userOAuth2) {

        User existData = userRepository.findByEmail(userOAuth2.getEmail()).orElse(null);
        //첫 로그인일 시
        if (existData == null) {
            User user = User.builder()
                    .email(userOAuth2.getEmail())
                    .name(userOAuth2.getName())
                    .build();
            User savedUser = userRepository.register(user);
            return savedUser;
        }
        //이미 정보가 있을 시
        else {

            return existData;
        }
    }
}
