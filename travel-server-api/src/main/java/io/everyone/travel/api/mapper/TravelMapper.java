package io.everyone.travel.api.mapper;

import io.everyone.travel.api.controller.dto.*;
import io.everyone.travel.core.domain.Travel;
import io.everyone.travel.core.domain.enums.Nation;
import io.everyone.travel.core.util.EnumSupports;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TravelMapper {


    /**
     * 여행쓰기요청 -> 여행엔티티 변환
     */
    public Travel toEntity(TravelWriteRequest from) {
        return Travel.builder()
            .title(from.title())
            .startAt(from.startAt())
            .endAt(from.endAt())
            .nation(EnumSupports.byEnumName(Nation.class, from.nation()))
            .build();
    }

    /**
     * 여행엔티티 -> 여행작성응답 변환
     */
    public TravelWriteResponse toWriteResponse(Travel from) {
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
    public TravelUpdateResponse toUpdateResponse(Travel from) {
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
    public TravelResponse.TravelView toView(Travel from) {
        return TravelResponse.TravelView.builder()
            .id(from.getId())
            .title(from.getTitle())
            .startAt(from.getStartAt())
            .endAt(from.getEndAt())
            .nation(NationModel.of(from.getNation()))
            .thumbnail(from.getThumbnail())
            .plans(
                from.getPlans().stream()
                    .map(PlanMapper::toView)
                    .toList()
            )
            .expenses(
                from.getExpenses().stream()
                    .map(ExpenseMapper::toView)
                    .toList()
            )
            .build();
    }

}
