package com.petweb.sponge.user.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAddressResponse {

    private String city;
    private String town;


}
