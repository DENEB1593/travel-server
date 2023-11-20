package io.everyone.travel.mapper;

import io.everyone.travel.controller.dto.*;
import io.everyone.travel.domain.Travel;
import io.everyone.travel.domain.enums.Nation;
import io.everyone.travel.util.EnumUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TravelMapper {


    /**
     * 여행쓰기요청 -> 여행엔티티 변환
     */
    public static Travel toEntity(TravelWriteRequest from) {
        return Travel.builder()
            .title(from.title())
            .startAt(from.startAt())
            .endAt(from.endAt())
            .nation(EnumUtil.byEnumName(Nation.class, from.nation()))
            .build();
    }

    /**
     * 여행엔티티 -> 여행작성응답 변환
     */
    public static TravelWriteResponse toWriteResponse(Travel from) {
        return TravelWriteResponse.builder()
            .id(from.getId())
            .title(from.getTitle())
            .startAt(from.getStartAt())
            .endAt(from.getEndAt())
            .nation(NationModel.of(from.getNation()))
            .createdAt(from.getCreatedAt())
            .modifiedAt(from.getModifiedAt())
            .build();
    }

    /**
     * 여행엔티티 -> 여행수정응답 변환
     */
    public static TravelUpdateResponse toUpdateResponse(Travel from) {
        return TravelUpdateResponse.builder()
            .id(from.getId())
            .title(from.getTitle())
            .startAt(from.getStartAt())
            .endAt(from.getEndAt())
            .nation(NationModel.of(from.getNation()))
            .createdAt(from.getCreatedAt())
            .modifiedAt(from.getModifiedAt())
            .build();
    }

    /**
     * 여행엔티티 -> 여행뷰모델 변환
     */
    public static TravelView toView(Travel from) {
        return TravelView.builder()
            .id(from.getId())
            .title(from.getTitle())
            .startAt(from.getStartAt())
            .endAt(from.getEndAt())
            .nation(NationModel.of(from.getNation()))
            .thumbnail(from.getThumbnail())
            .plans(from.getPlans().stream().map(PlanMapper::toView).toList())
            .expenses(from.getExpenses().stream().map(ExpenseMapper::toView).toList())
            .build();
    }

}
