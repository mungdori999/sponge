package com.petweb.sponge.pet.repository;

import com.petweb.sponge.pet.dto.PetDTO;
import com.petweb.sponge.user.repository.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity userEntity;

    public void setPetImgUrl(String petImgUrl) {
        this.petImgUrl = petImgUrl;
    }

    public void updatePet(PetDTO petDTO) {
        this.name = petDTO.getPetName();
        this.breed = petDTO.getBreed();
        this.gender = petDTO.getGender();
        this.age = petDTO.getAge();
        this.weight = petDTO.getAge();
        this.petImgUrl = petDTO.getPetImgUrl();
    }

    @Builder
    public PetEntity(String name, String breed, int gender, int age, float weight, String petImgUrl, UserEntity userEntity) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.petImgUrl = petImgUrl;
        this.userEntity = userEntity;
    }
}
