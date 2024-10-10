package com.petweb.sponge.pet.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.petweb.sponge.exception.error.LoginIdError;
import com.petweb.sponge.exception.error.NotFoundPet;
import com.petweb.sponge.pet.repository.PetEntity;
import com.petweb.sponge.pet.dto.PetDTO;
import com.petweb.sponge.pet.repository.PetRepository;
import com.petweb.sponge.user.repository.UserEntity;
import com.petweb.sponge.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    /**
     * 펫 정보 단건 조회
     *
     * @param petId
     * @return
     */
    @Transactional(readOnly = true)
    public PetDTO findPet(Long petId) {
        PetEntity petEntity = petRepository.findById(petId).orElseThrow(
                NotFoundPet::new);
        return toDto(petEntity);
    }

    /**
     * 펫 userId 해당 건 전체 조회
     *
     * @param userId
     * @return
     */
    @Transactional(readOnly = true)
    public List<PetDTO> findAllPet(Long userId) {
        List<PetEntity> petEntityList = petRepository.findAllByUserEntityId(userId);
        return petEntityList.stream().map(pet -> toDto(pet)).collect(Collectors.toList());

    }

    /**
     * 펫 정보 저장
     *
     * @param loginId
     * @param petDTO
     */
    @Transactional
    public void savePet(Long loginId, PetDTO petDTO) {
        //현재 로그인 유저 정보 가져오기
//        UserEntity userEntity = userRepository.findById(loginId).orElseThrow(
//                () -> new NotFoundException("NO Found USER"));
//        PetEntity petEntity = PetEntity.builder()
//                .name(petDTO.getPetName())
//                .breed(petDTO.getBreed())
//                .gender(petDTO.getGender())
//                .age(petDTO.getAge())
//                .weight(petDTO.getWeight())
//                .userEntity(userEntity)
//                .build();
//        //반려견 저장
//        petRepository.save(petEntity);
    }

    /**
     * 펫 정보 업데이트
     * @param loginId
     * @param petId
     * @param petDTO
     */
    @Transactional
    public void updatePet(Long loginId, Long petId, PetDTO petDTO) {
        PetEntity petEntity = petRepository.findById(petId).orElseThrow(NotFoundPet::new);
        if (!petEntity.getUserEntity().getId().equals(loginId)) {
            throw new LoginIdError();
        }
        petEntity.updatePet(petDTO);
    }

    /**
     * 펫 정보 삭제
     *
     * @param loginId
     * @param petId
     */
    @Transactional
    public void deletePet(Long loginId, Long petId) {
        PetEntity petEntity = petRepository.findById(petId).orElseThrow(NotFoundPet::new);
        if (!petEntity.getUserEntity().getId().equals(loginId)) {
            throw new LoginIdError();
        }
        petRepository.deleteById(petId);
    }

    /**
     * 펫 이미지 삭제
     * @param loginId
     * @param petId
     */
    @Transactional
    public void deletePetImg(Long loginId, Long petId) {
        PetEntity petEntity = petRepository.findById(petId).orElseThrow(NotFoundPet::new);
        if (!petEntity.getUserEntity().getId().equals(loginId)) {
            throw new LoginIdError();
        }
        petEntity.setPetImgUrl(null);
    }

    private PetDTO toDto(PetEntity petEntity) {
        return PetDTO.builder()
                .petId(petEntity.getId())
                .petName(petEntity.getName())
                .breed(petEntity.getBreed())
                .gender(petEntity.getGender())
                .age(petEntity.getAge())
                .weight(petEntity.getWeight())
                .build();
    }



}
