package com.petweb.sponge.pet.domain;


import com.petweb.sponge.exception.error.LoginIdError;
import com.petweb.sponge.pet.dto.PetCreate;
import com.petweb.sponge.pet.dto.PetUpdate;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Pet {

    private Long id;
    private String name;
    private String breed;
    private int gender;
    private int age;
    private float weight;
    private String petImgUrl;
    private Long userId;

    private Timestamp createdAt;
    private Timestamp modifiedAt;

    @Builder
    public Pet(Long id, String name, String breed, int gender, int age, float weight, String petImgUrl, Long userId, Timestamp createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.petImgUrl = petImgUrl;
        this.userId = userId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Pet from(Long userId, PetCreate petCreate) {
            return Pet.builder()
                    .name(petCreate.getName())
                    .breed(petCreate.getBreed())
                    .gender(petCreate.getGender())
                    .age(petCreate.getAge())
                    .weight(petCreate.getWeight())
                    .petImgUrl(petCreate.getPetImgUrl())
                    .userId(userId)
                    .build();
    }

    public Pet update(PetUpdate petUpdate, Long loginId) {

        if (!userId.equals(loginId)) {
            throw new LoginIdError();
        }
        return Pet.builder()
                .id(id)
                .name(petUpdate.getName())
                .breed(petUpdate.getBreed())
                .gender(petUpdate.getGender())
                .age(petUpdate.getAge())
                .weight(petUpdate.getWeight())
                .petImgUrl(petUpdate.getPetImgUrl())
                .userId(userId)
                .createdAt(createdAt)
                .build();
    }
}
