package io.everyone.travel.controller;

import io.everyone.travel.controller.common.CommonResponse;
import io.everyone.travel.controller.dto.NationModel;
import io.everyone.travel.domain.enums.Nation;
import io.everyone.travel.exception.model.ProblemResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Tag(name = "코드 API")
@RestController
@RequestMapping("api/code")
public class CodeController {


    @Operation(
            summary = "국가 코드 조회",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "조회 성공",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 오류",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemResponseModel.class))
                    ),
            }
    )
    @GetMapping("/nation")
    public CommonResponse<List<NationModel>> nation() {
        return CommonResponse.OK(
                Arrays.stream(Nation.values()).map(NationModel::of).toList());
    }

}