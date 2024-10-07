package com.petweb.sponge.user.dto;

import com.petweb.sponge.pet.dto.PetDTO;
import com.petweb.sponge.trainer.dto.AddressDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserDetailDTO {

    private Long userId;
    private String userName;
    private List<PetDTO> petList;
    private List<AddressDTO> addressList;
}
