package com.petweb.sponge.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshRepositoryImpl implements RefreshRepository {

    private final RefreshJpaRepository refreshJpaRepository;

    @Override
    public RefreshTokenEntity save(String refreshToken) {
         return refreshJpaRepository.save(RefreshTokenEntity.builder().refreshToken(refreshToken).build());
    }

    @Override
    public Boolean existsByRefresh(String refreshToken) {
        return refreshJpaRepository.existsByRefreshToken(refreshToken);
    }

    @Override
    public void deleteByRefresh(String refreshToken) {
        refreshJpaRepository.deleteByRefreshToken(refreshToken);
    }
}
