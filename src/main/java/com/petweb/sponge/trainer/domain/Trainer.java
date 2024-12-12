package com.petweb.sponge.trainer.domain;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
public class Trainer {

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
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private List<TrainerAddress> trainerAddressList;

    @Builder
    public Trainer(Long id, String email, String name, int gender, String phone, String profileImgUrl, String content, int years, int adoptCount, int chatCount, Timestamp createdAt, Timestamp modifiedAt, List<TrainerAddress> trainerAddressList) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.profileImgUrl = profileImgUrl;
        this.content = content;
        this.years = years;
        this.adoptCount = adoptCount;
        this.chatCount = chatCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.trainerAddressList = trainerAddressList;
    }
}
