package com.petweb.sponge.trainer.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TrainerAddress {

    private Long id;
    private String city;
    private String town;

    @Builder
    public TrainerAddress(Long id, String city, String town) {
        this.id = id;
        this.city = city;
        this.town = town;
    }
}
