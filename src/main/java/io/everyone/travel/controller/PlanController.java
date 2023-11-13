package io.everyone.travel.controller;


import io.everyone.travel.controller.common.CommonResponse;
import io.everyone.travel.controller.dto.PlanView;
import io.everyone.travel.exception.model.ProblemResponseModel;
import io.everyone.travel.mapper.PlanMapper;
import io.everyone.travel.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "계획 API")
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
            ),
            @ApiResponse(
                responseCode = "404",
                description = "조회 불가",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProblemResponseModel.class)
                )
            ),
        }
    )
    @GetMapping("/{travelId}")
    public CommonResponse<List<PlanView>> find(
        @PathVariable Long travelId
    ) {
        return CommonResponse.OK(
            planService.findByTravelId(travelId)
                .stream()
                .map(PlanMapper::toView)
                .toList()
        );
    }


}
