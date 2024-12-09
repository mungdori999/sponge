package com.petweb.sponge.trainer.repository;

import com.petweb.sponge.post.domain.post.PostFile;
import com.petweb.sponge.post.repository.post.PostFileEntity;
import com.petweb.sponge.trainer.domain.History;
import com.petweb.sponge.trainer.domain.Trainer;
import com.petweb.sponge.trainer.domain.TrainerAddress;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "trainers")
@EntityListeners(AuditingEntityListener.class)
public class TrainerEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String email; //로그인 아이디
    private String name; //이름
    private int gender; //성별
    private String phone; //핸드폰 번호
    private String profileImgUrl; //프로필 이미지 링크
    private String content; //자기소개
    private int years; //연차

    private int adoptCount; // 채택 답변 수
    private int chatCount; // 1대1 채팅 수
    @CreatedDate
    private Timestamp createdAt;
    @LastModifiedDate
    private Timestamp modifiedAt;

    @OneToMany(mappedBy = "trainerEntity", cascade = CascadeType.ALL)
    private List<HistoryEntity> historyEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "trainerEntity", cascade = CascadeType.ALL)
    private List<TrainerAddressEntity> trainerAddressEntityList = new ArrayList<>();

    @Builder
    public TrainerEntity(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public void addTrainerAddressList(List<TrainerAddressEntity> trainerAddressEntityList) {
            this.trainerAddressEntityList = trainerAddressEntityList;
    }
    public void addHistoryList(List<HistoryEntity> historyEntityList) {
            this.historyEntityList = historyEntityList;
    }
    public Trainer toModel() {

        List<TrainerAddress> trainerAddressList =(!Hibernate.isInitialized(trainerAddressEntityList) || trainerAddressEntityList == null || trainerAddressEntityList.isEmpty())
                ? Collections.emptyList()
                : trainerAddressEntityList.stream()
                .map(TrainerAddressEntity::toModel)
                .collect(Collectors.toList());


        List<History> historyList =(!Hibernate.isInitialized(historyEntityList) || historyEntityList == null || historyEntityList.isEmpty())
                ? Collections.emptyList()
                : historyEntityList.stream()
                .map(HistoryEntity::toModel)
                .collect(Collectors.toList());

        return Trainer.builder()
                .id(id)
                .email(email)
                .name(name)
                .gender(gender)
                .phone(phone)
                .profileImgUrl(profileImgUrl)
                .content(content)
                .years(years)
                .adoptCount(adoptCount)
                .chatCount(chatCount)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .trainerAddressList(trainerAddressList)
                .historyList(historyList)
                .build();

    }



}
