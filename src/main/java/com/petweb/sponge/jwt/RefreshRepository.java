package com.petweb.sponge.jwt;


import org.springframework.transaction.annotation.Transactional;

public interface RefreshRepository {

    @Transactional
    RefreshTokenEntity save(String refreshToken);
    @Transactional(readOnly = true)
    Boolean existsByRefresh(String refreshToken);
    @Transactional
    void deleteByRefresh(String refreshToken);
}
