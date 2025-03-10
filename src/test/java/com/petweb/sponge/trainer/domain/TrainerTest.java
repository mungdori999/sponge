package com.petweb.sponge.trainer.domain;

import com.petweb.sponge.trainer.dto.HistoryCreate;
import com.petweb.sponge.trainer.dto.TrainerAddressCreate;
import com.petweb.sponge.trainer.dto.TrainerCreate;
import com.petweb.sponge.utils.Gender;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class TrainerTest {


    @Test
    public void trainerCreate로_TRAINER를_생성할_수_있다() {

        // given
        TrainerCreate trainerCreate = TrainerCreate.builder()
                .email("test@example.com")
                .name("김철수")
                .gender(Gender.MALE.getCode())  // 1: 남성, 2: 여성
                .phone("01012345678")
                .profileImgUrl("")
                .content("안녕하세요! 전문 트레이너입니다.")
                .years(5) // 연차
                .trainerAddressList(List.of(
                        TrainerAddressCreate.builder()
                                .city("서울")
                                .town("강남구")
                                .build(),
                        TrainerAddressCreate.builder()
                                .city("부산")
                                .town("해운대구")
                                .build()
                ))
                .historyList(List.of(
                        HistoryCreate.builder()
                                .title("헬스 트레이너")
                                .startDt("201901")
                                .endDt("202312")
                                .description("헬스 트레이너로 5년간 활동")
                                .build(),
                        HistoryCreate.builder()
                                .title("PT 강사")
                                .startDt("202105")
                                .endDt("202312")
                                .description("개인 PT 강사로 활동")
                                .build()
                ))
                .build();

        // when
        Trainer trainer = Trainer.from(trainerCreate);

        // then
        assertThat(trainer).isNotNull();
        assertThat(trainer.getEmail()).isEqualTo("test@example.com");
        assertThat(trainer.getName()).isEqualTo("김철수");
        assertThat(trainer.getGender()).isEqualTo(Gender.MALE.getCode());
        assertThat(trainer.getPhone()).isEqualTo("01012345678");
        assertThat(trainer.getProfileImgUrl()).isEqualTo("");
        assertThat(trainer.getContent()).isEqualTo("안녕하세요! 전문 트레이너입니다.");
        assertThat(trainer.getYears()).isEqualTo(5);

        // 주소 리스트 검증
        assertThat(trainer.getTrainerAddressList()).isNotNull();
        assertThat(trainer.getTrainerAddressList().size()).isEqualTo(2);
        assertThat(trainer.getTrainerAddressList().get(0).getCity()).isEqualTo("서울");
        assertThat(trainer.getTrainerAddressList().get(0).getTown()).isEqualTo("강남구");
        assertThat(trainer.getTrainerAddressList().get(1).getCity()).isEqualTo("부산");
        assertThat(trainer.getTrainerAddressList().get(1).getTown()).isEqualTo("해운대구");

        // 경력 리스트 검증
        assertThat(trainer.getHistoryList()).isNotNull();
        assertThat(trainer.getHistoryList().size()).isEqualTo(2);
        assertThat(trainer.getHistoryList().get(0).getTitle()).isEqualTo("헬스 트레이너");
        assertThat(trainer.getHistoryList().get(0).getStartDt()).isEqualTo("201901");
        assertThat(trainer.getHistoryList().get(0).getEndDt()).isEqualTo("202312");
        assertThat(trainer.getHistoryList().get(0).getDescription()).isEqualTo("헬스 트레이너로 5년간 활동");

        assertThat(trainer.getHistoryList().get(1).getTitle()).isEqualTo("PT 강사");
        assertThat(trainer.getHistoryList().get(1).getStartDt()).isEqualTo("202105");
        assertThat(trainer.getHistoryList().get(1).getEndDt()).isEqualTo("202312");
        assertThat(trainer.getHistoryList().get(1).getDescription()).isEqualTo("개인 PT 강사로 활동");


    }
}
