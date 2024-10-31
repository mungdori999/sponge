package com.petweb.sponge.pet.service;

import com.petweb.sponge.exception.error.NotFoundPet;
import com.petweb.sponge.pet.domain.Pet;
import com.petweb.sponge.pet.dto.PetCreate;
import com.petweb.sponge.pet.dto.PetUpdate;
import com.petweb.sponge.pet.mock.MockPetRepository;
import com.petweb.sponge.pet.service.port.PetRepository;
import com.petweb.sponge.user.domain.User;
import com.petweb.sponge.user.mock.MockUserRepository;
import com.petweb.sponge.user.service.port.UserRepository;
import com.petweb.sponge.utils.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class PetServiceTest {

    private PetService petService;

    @BeforeEach()
    void init() {
        PetRepository petRepository = new MockPetRepository();
        UserRepository userRepository = new MockUserRepository();
        petService = PetService.builder()
                .petRepository(petRepository)
                .userRepository(userRepository)
                .build();
        User user = User.builder()
                .id(1L)
                .email("abc@test.com")
                .name("테스트").build();
        userRepository.save(user);

        petRepository.save(Pet.builder()
                .name("테스트 이름")
                .gender(Gender.MALE.getCode())
                .breed("테스트 견종")
                .age(5)
                .userId(user.getId())
                .build());
    }

    @Test
    public void getById는_PET을_조회한다() {
        // given
        Long id = 1L;

        // when
        Pet result = petService.getById(id);

        // then
        assertThat(result.getName()).isEqualTo("테스트 이름");

    }

    @Test
    public void create는_PET의_정보를_저장한다() {
        // given
        Long loginId = 1L;
        PetCreate petCreate = PetCreate.builder()
                .name("테스트 이름")
                .breed("테스트 견종")
                .gender(Gender.MALE.getCode())
                .age(5)
                .weight(10.5f)
                .petImgUrl(null)
                .build();

        // when
        Pet result = petService.create(loginId, petCreate);

        // then
        assertThat(result.getName()).isEqualTo("테스트 이름");
        assertThat(result.getBreed()).isEqualTo("테스트 견종");
    }

    @Test
    public void update는_유저의_정보를_수정한다() {
        // given
        Long loginId = 1L;
        Long id = 1L;
        PetUpdate petUpdate = PetUpdate.builder()
                .name("수정 이름")
                .breed("수정 견종")
                .gender(Gender.NEUTERED_MALE.getCode())
                .age(15)
                .weight(20.4f)
                .petImgUrl("https").build();

        // when
        Pet result = petService.update(loginId, id, petUpdate);

        // then
        assertThat(result.getName()).isEqualTo("수정 이름");
        assertThat(result.getGender()).isEqualTo(Gender.NEUTERED_MALE.getCode());
    }

    @Test
    public void delete는_유저의_정보를_삭제한다() {
        // given
        Long loginId = 1L;
        Long id =1L;

        // when
        petService.delete(loginId,id);

        // then
        assertThatThrownBy(() -> petService.getById(id)).isInstanceOf(NotFoundPet.class);

    }
}
