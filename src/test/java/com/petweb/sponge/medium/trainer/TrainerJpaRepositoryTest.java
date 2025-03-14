package com.petweb.sponge.medium.trainer;

import com.petweb.sponge.trainer.repository.TrainerEntity;
import com.petweb.sponge.trainer.repository.TrainerJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest(showSql = true)
@Sql("/sql/trainer-repository-test-data.sql")
public class TrainerJpaRepositoryTest {

    @Autowired
    private TrainerJpaRepository trainerJpaRepository;

    @Test
    public void findByEmail은_email로_훈련사를_찾는다() {
        // given
        String email = "trainer1@test.com";

        // when
        Optional<TrainerEntity> result = trainerJpaRepository.findByEmail(email);

        // then
        assertThat(result).isPresent(); // 값이 존재하는지 검증
        assertThat(result.get().getEmail()).isEqualTo(email);
    }

    @Test
    public void findShortByIdList는_id여러개로_훈련사를_찾는다() {
        // given
        List<Long> idList = new ArrayList<>(List.of(1L, 2L));

        // when
        List<TrainerEntity> result = trainerJpaRepository.findShortByIdList(idList);

        // then
        assertThat(result).hasSize(2);
    }


}
