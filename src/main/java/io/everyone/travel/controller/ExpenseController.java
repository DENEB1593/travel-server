package io.everyone.travel.controller;

import io.everyone.travel.controller.common.CommonResponse;
import io.everyone.travel.controller.dto.ExpenseView;
import io.everyone.travel.controller.dto.ExpenseWriteRequest;
import io.everyone.travel.controller.dto.ExpenseWriteResponse;
import io.everyone.travel.exception.model.ProblemResponseModel;
import io.everyone.travel.mapper.ExpenseMapper;
import io.everyone.travel.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "지출 API")
@RestController
@RequestMapping("api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;


    @Operation(
        summary = "지출 정보 저장",
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
    public CommonResponse<ExpenseWriteResponse> save(
        @RequestBody @Valid ExpenseWriteRequest request
    ) {
        return CommonResponse.OK(
            ExpenseMapper.toResponse(
                expenseService.save(request)
            )
        );
    }



    @Operation(
        summary = "지출 목록 조회",
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

    @GetMapping("/{travelId}")
    public CommonResponse<List<ExpenseView>> findAll(
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
        summary = "지출 정보 삭제",
        description = "지출 정보를 삭제한다",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "삭제 성공",
                useReturnTypeSchema = true
            )
        }
    )
    @DeleteMapping("/{expenseId}")
    public CommonResponse<?> delete(
        @PathVariable Long expenseId
    ) {
        expenseService.deleteById(expenseId);
        return CommonResponse.OK("삭제 완료");
    }
}
