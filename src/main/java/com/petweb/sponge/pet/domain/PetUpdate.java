package com.petweb.sponge.pet.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PetUpdate {
    private String name;
    private String breed;
    private int gender;
    private int age;
    private float weight;
    private String petImgUrl;
}