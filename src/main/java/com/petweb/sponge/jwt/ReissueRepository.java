package com.petweb.sponge.jwt;


public interface ReissueRepository {


    Boolean existsByRefresh(String refresh);

    void deleteByRefresh(String refresh);
}
