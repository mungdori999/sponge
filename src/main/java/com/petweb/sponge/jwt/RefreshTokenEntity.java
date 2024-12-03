package com.petweb.sponge.jwt;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "refresh_token")
public class RefreshTokenEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String refreshToken;

    @Builder
    public RefreshTokenEntity(Long id, String refreshToken) {
        this.id = id;
        this.refreshToken = refreshToken;
    }
}
