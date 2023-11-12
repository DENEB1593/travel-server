package io.everyone.travel.controller;


import io.everyone.travel.controller.dto.CommonResponse;
import io.everyone.travel.controller.dto.TravelView;
import io.everyone.travel.mapper.PlanMapper;
import io.everyone.travel.mapper.TravelMapper;
import io.everyone.travel.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "계획정보 정보 API")
@RestController
@RequestMapping("api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @Operation(
            summary = "계획 목록 조회",
            description = "여행ID로 등록된 계획 목록을 조회한다",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "조회 성공",
                            useReturnTypeSchema = true
                    )
            }
    )
    @GetMapping("/{travelId}")
    public CommonResponse<List<TravelView.PlanView>> find(
            @PathVariable Long travelId
    ) {
        return CommonResponse.OK(
                planService.findByTravelId(travelId)
                        .map(plans ->
                                plans.stream().map(PlanMapper::toView).toList()
                        )
                        .orElseThrow(RuntimeException::new) // 커스텀 예외는 추후 개발, 우선은 Runtime 으로
        );
    }


}
