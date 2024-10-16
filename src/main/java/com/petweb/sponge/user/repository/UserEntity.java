package com.petweb.sponge.user.repository;

import com.petweb.sponge.user.domain.User;
import com.petweb.sponge.user.domain.UserAddress;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String email; //로그인 아이디
    private String name; //이름

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<UserAddressEntity> userAddressEntityList = new ArrayList<>();

    @CreatedDate
    private Timestamp createdAt;
    @LastModifiedDate
    private Timestamp modifiedAt;

    @Builder
    public UserEntity(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public User toModel() {
        List<UserAddress> userAddressList = userAddressEntityList.stream().map((userAddressEntity) -> UserAddress.builder()
                .id(userAddressEntity.getId())
                .city(userAddressEntity.getCity())
                .town(userAddressEntity.getTown()).build()).collect(Collectors.toList());

        return User.builder()
                .id(id)
                .email(email)
                .name(name)
                .userAddressList(userAddressList)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }

    public static UserEntity from(User user) {
        List<UserAddressEntity> userAddressEntities
                = user.getUserAddressList().stream().map(UserAddressEntity::from).collect(Collectors.toList());
        UserEntity userEntity = new UserEntity();
        userEntity.id = user.getId();
        userEntity.email = user.getEmail();
        userEntity.name = user.getName();
        userEntity.userAddressEntityList = userAddressEntities;
        userEntity.createdAt = user.getCreatedAt();
        userAddressEntities.forEach(userAddressEntity -> userAddressEntity.setUserEntity(userEntity));
        return userEntity;
    }
}
