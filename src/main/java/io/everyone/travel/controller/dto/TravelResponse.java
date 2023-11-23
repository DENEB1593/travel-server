package io.everyone.travel.controller.dto;

import io.everyone.travel.domain.Travel;
import io.everyone.travel.mapper.TravelMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class TravelResponse {

    @Schema(description = "여행 정보 목록", implementation = TravelView.class)
    List<TravelView> travels;

    @Schema(description = "현재 페이지 번호", example = "1")
    int page;       // 페이지 번호

    @Schema(description = "현재 페이지 내 데이터 개수", example = "10")
    int size;       // 페이지 내 데이터 길이

    @Schema(description = "총 페이지 개수", example = "5")
    int totalPage;

    @Schema(description = "총 데이터 개수", example = "50")
    int totalSize;

    public static TravelResponse of(Page<Travel> travelPage) {
        return TravelResponse.builder()
            .travels(travelPage.getContent().stream().map(TravelMapper::toView).toList())
            .page(travelPage.getPageable().getPageNumber() + 1)
            .size(travelPage.getNumberOfElements())
            .totalPage(travelPage.getTotalPages())
            .totalSize((int) travelPage.getTotalElements())
            .build();
    }

    @Value
    @Builder
    public static class TravelView {

        Long id;

        LocalDateTime startAt;

        LocalDateTime endAt;

        String title;

        String thumbnail;

        NationModel nation;

        List<PlanView> plans;

        List<ExpenseView> expenses;


    }


}
