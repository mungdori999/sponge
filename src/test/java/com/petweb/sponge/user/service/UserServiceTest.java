package com.petweb.sponge.user.service;

import com.petweb.sponge.exception.error.NotFoundUser;
import com.petweb.sponge.user.domain.User;
import com.petweb.sponge.user.domain.UserAddress;
import com.petweb.sponge.user.domain.UserUpdate;
import com.petweb.sponge.user.mock.MockUserRepository;
import com.petweb.sponge.user.service.port.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UserServiceTest {

    private UserService userService;

    @BeforeEach()
    void init() {
        UserRepository userRepository = new MockUserRepository();
        userService = UserService.builder()
                .userRepository(userRepository)
                .build();
        userRepository.save(User.builder()
                .email("abc@naver.com")
                .name("테스트").build());

    }

    @Test
    public void getById는_가입_USER의_정보를_가져온다() {
        // given
        Long id = 1L;

        // when
        User result = userService.getById(id);

        // then
        assertThat(result.getEmail()).isEqualTo("abc@naver.com");
    }

    @Test
    public void update는_USER의_정보를_수정한다() {
        // given
        Long id = 1L;
        List<UserAddress> userAddressList = new ArrayList<>();
        userAddressList.add(UserAddress.builder()
                .id(1L)
                .city("테스트구")
                .town("테스트동").build());
        UserUpdate userUpdate = UserUpdate.builder()
                .name("test")
                .addressList(userAddressList)
                .build();

        // when
        User result = userService.update(id, userUpdate);

        // then
        assertThat(result.getName()).isEqualTo("test");
        assertThat(result.getUserAddressList().get(0).getCity()).isEqualTo("테스트구");

    }

    @Test
    public void delete는_USER의_정보를_삭제한다() {
        // given
        Long id = 1L;

        // when
        userService.delete(id);

        // then
        assertThatThrownBy(() -> userService.getById(id)).isInstanceOf(NotFoundUser.class);
    }

}