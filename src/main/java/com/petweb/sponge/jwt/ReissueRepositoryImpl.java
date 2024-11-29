package com.petweb.sponge.jwt;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReissueRepositoryImpl implements ReissueRepository {

    private final ReissueJpaRepository reissueJpaRepository;

    @Override
    public Boolean existsByRefresh(String refresh) {
        return null;
    }

    @Override
    public void deleteByRefresh(String refresh) {

    }
}
