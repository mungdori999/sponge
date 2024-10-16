package com.petweb.sponge.user.controller.response;

import com.petweb.sponge.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class UserResponse {

    private Long id;
    private String name;
    private List<UserAddressResponse> userAddressList;

    public static UserResponse from(User user) {
        List<UserAddressResponse> userAddressResponseList = user.getUserAddressList().stream().map(userAddress -> UserAddressResponse.builder()
                .city(userAddress.getCity())
                .town(userAddress.getTown()).build()).collect(Collectors.toList());
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .userAddressList(userAddressResponseList)
                .build();
    }

}
