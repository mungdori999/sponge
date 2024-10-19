package com.petweb.sponge.pet.domain;

import lombok.Getter;

@Getter
public class PetCreate {
    private String name;
    private String breed;
    private int gender;
    private int age;
    private float weight;
    private String petImgUrl;
}
