package io.everyone.travel.api.web.travel.mapper;

import io.everyone.travel.api.web.expense.mapper.ExpenseApiMapper;
import io.everyone.travel.api.web.plan.mapper.PlanApiMapper;
import io.everyone.travel.api.web.travel.dto.*;
import io.everyone.travel.core.domain.travel.entity.Travel;
import io.everyone.travel.core.domain.travel.dto.UpdateTravel;
import io.everyone.travel.core.domain.travel.dto.WriteTravel;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

@UtilityClass
public class TravelApiMapper {


    /**
     * 여행쓰기요청 -> 여행엔티티 변환
     */
    public WriteTravel toWriteTravel(TravelWriteRequest from, MultipartFile thumbnail) {
        return WriteTravel.builder()
            .title(from.title())
            .startAt(from.startAt())
            .endAt(from.endAt())
            .nation(from.nation())
            .thumbnail(thumbnail)
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
                    .map(PlanApiMapper::toView)
                    .toList()
            )
            .expenses(
                from.getExpenses().stream()
                    .map(ExpenseApiMapper::toView)
                    .toList()
            )
            .build();
    }

    public UpdateTravel toUpdateTravel(Long travelId, TravelUpdateRequest from) {
        return UpdateTravel.builder()
            .travelId(travelId)
            .startAt(from.startAt())
            .endAt(from.endAt())
            .title(from.title())
            .nation(from.nation())
            .build();
    }
}
