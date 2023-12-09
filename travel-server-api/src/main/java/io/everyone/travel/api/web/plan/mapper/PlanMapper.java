package io.everyone.travel.api.web.plan.mapper;

import io.everyone.travel.api.web.plan.dto.*;
import io.everyone.travel.core.domain.plan.entity.Plan;
import io.everyone.travel.core.domain.plan.dto.UpdatePlan;
import io.everyone.travel.core.domain.plan.dto.WritePlan;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PlanMapper {

    /**
     * 계획쓰기요청 -> 계획엔티티 변환
     */
    public WritePlan toWritePlan(PlanWriteRequest from) {
        return WritePlan.builder()
            .travelId(from.travelId())
            .title(from.title())
            .memo(from.memo())
            .startAt(from.startAt())
            .endAt(from.endAt())
            .build();
    }

    /**
     * 계획엔티티 -> 계획쓰기응답 변환
     */
    public PlanWriteResponse toWriteResponse(Plan from) {
        return PlanWriteResponse.builder()
            .id(from.getId())
            .title(from.getTitle())
            .memo(from.getMemo())
            .startAt(from.getStartAt())
            .endAt(from.getEndAt())
            .travelId(from.getTravel().getId())
            .createdAt(from.getCreatedAt())
            .modifiedAt(from.getModifiedAt())
            .build();
    }

    /**
     * 계획엔티티 -> 계획수정응답 변환
     */
    public PlanUpdateResponse toUpdateResponse(Plan from) {
        return PlanUpdateResponse.builder()
            .id(from.getId())
            .title(from.getTitle())
            .memo(from.getMemo())
            .startAt(from.getStartAt())
            .endAt(from.getEndAt())
            .travelId(from.getTravel().getId())
            .createdAt(from.getCreatedAt())
            .modifiedAt(from.getModifiedAt())
            .build();
    }


    /**
     * 계획엔티티 -> 계획뷰모델 변환
     */
    public PlanView toView(Plan from) {
        return PlanView.builder()
            .id(from.getId())
            .title(from.getTitle())
            .memo(from.getMemo())
            .startAt(from.getStartAt())
            .endAt(from.getEndAt())
            .createdAt(from.getCreatedAt())
            .modifiedAt(from.getModifiedAt())
            .build();
    }

    public UpdatePlan toUpdatePlan(Long planId, PlanUpdateRequest from) {
        return UpdatePlan.builder()
            .planId(planId)
            .title(from.title())
            .memo(from.memo())
            .startAt(from.startAt())
            .endAt(from.endAt())
            .build();
    }
}
