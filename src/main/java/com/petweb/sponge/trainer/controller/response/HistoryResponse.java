package com.petweb.sponge.trainer.controller.response;

import com.petweb.sponge.trainer.domain.History;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class HistoryResponse {

    private Long id;

    private String title;
    private String startDt;
    private String endDt;
    private String description;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    public static HistoryResponse from(History history) {
        return HistoryResponse.builder()
                .id(history.getId())
                .title(history.getTitle())
                .startDt(history.getStartDt())
                .endDt(history.getEndDt())
                .description(history.getDescription())
                .createdAt(history.getCreatedAt())
                .modifiedAt(history.getModifiedAt())
                .build();
    }
}
