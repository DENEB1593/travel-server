package io.everyone.travel.api.web.plan;


import io.everyone.travel.api.web.CommonResponse;
import io.everyone.travel.api.exception.model.ProblemResponseModel;
import io.everyone.travel.api.web.plan.mapper.PlanApiMapper;
import io.everyone.travel.api.web.plan.dto.*;
import io.everyone.travel.core.domain.plan.dto.UpdatePlan;
import io.everyone.travel.core.domain.plan.dto.WritePlan;
import io.everyone.travel.core.exception.NotFoundException;
import io.everyone.travel.core.domain.plan.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "계획 API")
@RestController
@RequestMapping("api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @Operation(
        summary = "계획 정보 저장",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "저장 성공",
                useReturnTypeSchema = true
            )
        }
    )
    @ResponseStatus(CREATED)
    @PostMapping
    public CommonResponse<PlanWriteResponse> save(
        @RequestBody @Valid PlanWriteRequest request
    ) {
        WritePlan writePlan = PlanApiMapper.toWritePlan(request);

        return CommonResponse.OK(
            PlanApiMapper.toWriteResponse(
                planService.save(writePlan)
            )
        );
    }

    @Operation(
        summary = "계획 단건 조회",
        description = "계획 정보를 조회한다",
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
                    schema = @Schema(implementation = ProblemResponseModel.class)
                )
            )
        }
    )
    @GetMapping("/{planId}")
    public CommonResponse<PlanView> find(
        @PathVariable Long planId
    ) {
        return CommonResponse.OK(
            planService.findByPlanId(planId)
                .map(PlanApiMapper::toView)
                .orElseThrow(NotFoundException::forPlan)
        );
    }

    @Operation(
        summary = "계획 정보 수정",
        description = "계획 정보를 수정한다",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "수정 성공",
                useReturnTypeSchema = true
            )
        }
    )
    @PutMapping("/{planId}")
    public CommonResponse<PlanUpdateResponse> update(
        @PathVariable Long planId,
        @RequestBody PlanUpdateRequest request
    ) {
        UpdatePlan updatePlan = PlanApiMapper.toUpdatePlan(planId, request);

        return CommonResponse.OK(
            PlanApiMapper.toUpdateResponse(
                planService.updatePlan(updatePlan)
            )
        );
    }

    @Operation(
        summary = "계획 정보 삭제",
        description = "계획 정보를 삭제한다",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "삭제 성공",
                useReturnTypeSchema = true
            )
        }
    )
    @DeleteMapping("/{planId}")
    public CommonResponse<?> delete(
        @PathVariable Long planId
    ) {
        planService.deleteById(planId);
        return CommonResponse.OK("삭제 완료");
    }


}
