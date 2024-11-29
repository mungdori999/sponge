package com.petweb.sponge.utils;

import lombok.Getter;

@Getter
public enum ResponseHttpStatus {

    EXPIRE_TOKEN(4000);

    private final int code;

    ResponseHttpStatus(int code) {
        this.code = code;
    }
}
