package com.petweb.sponge.trainer.domain;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class History {

    private Long id;
    private String title;
    private String startDt;
    private String endDt;
    private String description;
    private Long trainerId;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

    @Builder
    public History(Long id, String title, String startDt, String endDt, String description, Timestamp createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.title = title;
        this.startDt = startDt;
        this.endDt = endDt;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
