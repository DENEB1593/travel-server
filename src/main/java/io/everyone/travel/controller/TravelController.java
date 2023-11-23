package io.everyone.travel.controller;

import io.everyone.travel.config.page.PageModel;
import io.everyone.travel.controller.common.CommonResponse;
import io.everyone.travel.controller.dto.*;
import io.everyone.travel.exception.NotFoundException;
import io.everyone.travel.exception.model.ProblemResponseModel;
import io.everyone.travel.mapper.ExpenseMapper;
import io.everyone.travel.mapper.PlanMapper;
import io.everyone.travel.mapper.TravelMapper;
import io.everyone.travel.service.ExpenseService;
import io.everyone.travel.service.PlanService;
import io.everyone.travel.service.TravelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "여행 API")
@Slf4j
@RestController
@RequestMapping("api/travels")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;
    private final PlanService planService;
    private final ExpenseService expenseService;

    @Operation(
        summary = "여행 정보 저장",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "저장 성공",
                useReturnTypeSchema = true
            )
        }
    )
    @ResponseStatus(CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResponse<TravelWriteResponse> save(
        @ModelAttribute @Valid TravelWriteRequest request
    ) {
        return CommonResponse.OK(
            TravelMapper.toWriteResponse(
                travelService.save(request)
            )
        );
    }

    @Operation(
        summary = "여행 정보 단건 조회",
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
            ),
        }
    )
    @GetMapping("/{travelId}")
    public CommonResponse<TravelView> find(
        @PathVariable Long travelId
    ) {
        return CommonResponse.OK(
            travelService.findById(travelId)
                .map(TravelMapper::toView)
                .orElseThrow(NotFoundException::forTravel)
        );
    }

    @Operation(
        summary = "여행 목록 조회",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "조회 성공",
                useReturnTypeSchema = true
            )
        }
    )
    @Parameters({
        @Parameter(name = "page", description = "페이지 번호", example = "1"),
        @Parameter(name = "size", description = "페이지 크기", example = "20")
    })
    @GetMapping
    public CommonResponse<List<TravelView>> findPaginated(
        @Parameter(hidden = true) PageModel pageModel
    ) {
        return CommonResponse.OK(
            travelService.findPaginated(
                    pageModel.getPage(),
                    pageModel.getSize()
                )
                .stream()
                .map(TravelMapper::toView)
                .toList()
        );
    }

    @Operation(
        summary = "여행 내 계획 목록 조회",
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
                    schema = @Schema(implementation = ProblemResponseModel.class)
                )
            ),
        }
    )
    @GetMapping("/{travelId}/plans")
    public CommonResponse<List<PlanView>> findPlans(
        @PathVariable Long travelId
    ) {
        return CommonResponse.OK(
            planService.findByTravelId(travelId)
                .stream()
                .map(PlanMapper::toView)
                .toList()
        );
    }

    @Operation(
        summary = "여행 내 지출 목록 조회",
        description = "여행ID로 등록된 지출 목록을 조회한다",
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
            ),
        }
    )
    @GetMapping("/{travelId}/expenses")
    public CommonResponse<List<ExpenseView>> findExpenses(
        @PathVariable Long travelId
    ) {
        return CommonResponse.OK(
            expenseService.findByTravelId(travelId)
                .stream()
                .map(ExpenseMapper::toView)
                .toList()
        );
    }


    @Operation(
        summary = "여행 정보 수정",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "수정 성공",
                useReturnTypeSchema = true
            )
        }
    )
    @PutMapping(path = "/{travelId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResponse<TravelUpdateResponse> update(
        @PathVariable Long travelId,
        @ModelAttribute @Valid TravelUpdateRequest request
    ) {
        return CommonResponse.OK(
            TravelMapper.toUpdateResponse(
                travelService.updateTravel(travelId, request)
            )
        );
    }

    @Operation(
        summary = "여행 정보 삭제",
        description = "여행 정보를 삭제한다",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "삭제 성공",
                useReturnTypeSchema = true
            )
        }
    )
    @DeleteMapping("/{travelId}")
    public CommonResponse<?> delete(
        @PathVariable Long travelId
    ) {
        travelService.deleteById(travelId);
        return CommonResponse.OK("삭제 완료");
    }


}
