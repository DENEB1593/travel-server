package io.everyone.travel.api.web.expense;

import io.everyone.travel.api.web.CommonResponse;
import io.everyone.travel.api.exception.model.ProblemResponseModel;
import io.everyone.travel.api.web.expense.mapper.ExpenseMapper;
import io.everyone.travel.api.web.expense.dto.*;
import io.everyone.travel.core.exception.NotFoundException;
import io.everyone.travel.core.domain.expense.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
            ExpenseMapper.toWriteResponse(
                expenseService.save(
                    request.amt(),
                    request.spendAt(),
                    request.travelId()
                )
            )
        );
    }


    @Operation(
        summary = "지출 단건 조회",
        description = "지출 정보를 조회한다",
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
    @GetMapping("/{expenseId}")
    public CommonResponse<ExpenseView> find(
        @PathVariable Long expenseId
    ) {
        return CommonResponse.OK(
            expenseService.findByExpenseId(expenseId)
                .map(ExpenseMapper::toView)
                .orElseThrow(NotFoundException::forExpense)
        );
    }


    @Operation(
        summary = "지출 정보 수정",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "수정 성공",
                useReturnTypeSchema = true
            )
        }
    )
    @PutMapping("/{expenseId}")
    public CommonResponse<ExpenseUpdateResponse> update(
        @PathVariable Long expenseId,
        @RequestBody @Valid ExpenseUpdateRequest request
    ) {
        return CommonResponse.OK(
            ExpenseMapper.toUpdateResponse(
                expenseService.updateExpense(
                    expenseId,
                    request.amt(),
                    request.spendAt(),
                    request.travelId()
                )
            )
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
