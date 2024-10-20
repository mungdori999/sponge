package com.petweb.sponge.pet.domain;


import com.petweb.sponge.user.domain.User;
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
    private User user;

    private Timestamp createdAt;
    private Timestamp modifiedAt;

    @Builder
    public Pet(Long id, String name, String breed, int gender, int age, float weight, String petImgUrl, User user, Timestamp createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.petImgUrl = petImgUrl;
        this.user = user;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Pet from(User user, PetCreate petCreate) {
            return Pet.builder()
                    .name(petCreate.getName())
                    .breed(petCreate.getBreed())
                    .gender(petCreate.getGender())
                    .age(petCreate.getAge())
                    .weight(petCreate.getWeight())
                    .petImgUrl(petCreate.getPetImgUrl())
                    .user(user)
                    .build();
    }

    public Pet update(PetUpdate petUpdate) {
        return Pet.builder()
                .id(id)
                .name(petUpdate.getName())
                .breed(petUpdate.getBreed())
                .gender(petUpdate.getGender())
                .age(petUpdate.getAge())
                .weight(petUpdate.getWeight())
                .petImgUrl(petUpdate.getPetImgUrl())
                .user(user)
                .createdAt(createdAt)
                .build();
    }
}
