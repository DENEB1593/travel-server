package io.everyone.travel.mapper;

import io.everyone.travel.controller.dto.PlanView;
import io.everyone.travel.controller.dto.PlanWriteRequest;
import io.everyone.travel.controller.dto.PlanWriteResponse;
import io.everyone.travel.domain.Plan;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanMapper {

    /**
     * 계획쓰기요청 -> 계획엔티티 변환
     */
    public static Plan toEntity(PlanWriteRequest from) {
        return Plan.builder()
            .title(from.getTitle())
            .memo(from.getMemo())
            .startAt(from.getStartAt())
            .endAt(from.getEndAt())
            .build();
    }

    /**
     * 계획엔티티 -> 계획쓰기응답 변환
     */
    public static PlanWriteResponse toResponse(Plan from) {
        return PlanWriteResponse.builder()
            .title(from.getTitle())
            .memo(from.getMemo())
            .startAt(from.getStartAt())
            .endAt(from.getEndAt())
            .build();
    }

    /**
     * 계획엔티티 -> 계획뷰모델 변환
     */
    public static PlanView toView(Plan from) {
        return PlanView.builder()
            .id(from.getId())
            .title(from.getTitle())
            .memo(from.getMemo())
            .startAt(from.getStartAt())
            .endAt(from.getEndAt())
            .build();
    }

}
