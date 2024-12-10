package com.petweb.sponge.trainer.repository;

import com.petweb.sponge.trainer.domain.History;
import com.petweb.sponge.trainer.repository.TrainerEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "history")
@EntityListeners(AuditingEntityListener.class)
public class HistoryEntity {


    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String startDt;
    private String endDt;
    private String description;

    @CreatedDate
    private Timestamp createdAt;
    @LastModifiedDate
    private Timestamp modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private TrainerEntity trainerEntity;

    @Builder
    public HistoryEntity(String title, String startDt, String endDt, String description, TrainerEntity trainerEntity) {
        this.title = title;
        this.startDt = startDt;
        this.endDt = endDt;
        this.description = description;
        this.trainerEntity = trainerEntity;
    }


    public History toModel() {
        return History.builder()
                .id(id)
                .title(title)
                .startDt(startDt)
                .endDt(endDt)
                .description(description)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }
}