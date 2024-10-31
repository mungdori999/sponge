package com.petweb.sponge.pet.repository;

import com.petweb.sponge.pet.domain.Pet;
import com.petweb.sponge.user.repository.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "pets")
@EntityListeners(AuditingEntityListener.class)
public class PetEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name; // 반려견 이름
    private String breed; // 견종
    private int gender; // 성별
    private int age; // 나이
    private float weight; // 몸무게
    private String petImgUrl; // 이미지링크

    @CreatedDate
    private Timestamp createdAt;
    @LastModifiedDate
    private Timestamp modifiedAt;

    private Long userId;

    public static PetEntity from(Pet pet) {
        PetEntity petEntity = new PetEntity();
        petEntity.id = pet.getId();
        petEntity.name = pet.getName();
        petEntity.breed = pet.getBreed();
        petEntity.gender = pet.getGender();
        petEntity.age = pet.getAge();
        petEntity.weight = pet.getWeight();
        petEntity.petImgUrl = pet.getPetImgUrl();
        petEntity.userId = pet.getUserId();
        petEntity.createdAt = pet.getCreatedAt();
        return petEntity;
    }
    public Pet toModel() {
        return Pet.builder()
                .id(id)
                .name(name)
                .breed(breed)
                .gender(gender)
                .age(age)
                .weight(weight)
                .petImgUrl(petImgUrl)
                .userId(userId)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }

}
