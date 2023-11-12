package io.everyone.travel.mapper;

import io.everyone.travel.controller.dto.TravelView;
import io.everyone.travel.controller.dto.TravelWriteRequest;
import io.everyone.travel.controller.dto.TravelWriteResponse;
import io.everyone.travel.domain.Plan;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanMapper {

    /**
     * 계획쓰기요청 -> 계획엔티티 변환
     */
    public static Plan toEntity(TravelWriteRequest.PlanWriteRequest from) {
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
    public static TravelWriteResponse.PlanWriteResponse toResponse(Plan from) {
        return TravelWriteResponse.PlanWriteResponse.builder()
                .title(from.getTitle())
                .memo(from.getMemo())
                .startAt(from.getStartAt())
                .endAt(from.getEndAt())
                .build();
    }

    /**
     * 계획엔티티 -> 계획뷰모델 변환
     */
    public static TravelView.PlanView toView(Plan from) {
        return TravelView.PlanView.builder()
                .id(from.getId())
                .title(from.getTitle())
                .memo(from.getMemo())
                .startAt(from.getStartAt())
                .endAt(from.getEndAt())
                .build();
    }

}
