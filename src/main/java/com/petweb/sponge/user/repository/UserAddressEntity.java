package com.petweb.sponge.user.repository;

import com.petweb.sponge.user.domain.UserAddress;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "user_address")
public class UserAddressEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String town;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Builder
    public UserAddressEntity(String city, String town, UserEntity userEntity) {
        this.city = city;
        this.town = town;
        this.userEntity = userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public static UserAddressEntity from(UserAddress userAddress) {
        UserAddressEntity userAddressEntity = new UserAddressEntity();
        userAddressEntity.id = userAddress.getId();
        userAddressEntity.city = userAddress.getCity();
        userAddressEntity.town = userAddress.getTown();
        return userAddressEntity;
    }

}
