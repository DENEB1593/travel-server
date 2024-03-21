package io.everyone.travel.api.web.code;

import io.everyone.travel.api.exception.model.ProblemResponseModel;
import io.everyone.travel.api.web.CommonResponse;
import io.everyone.travel.api.web.travel.dto.NationModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Tag(name = "코드 API")
@RestController
@RequestMapping("api/code")
public class CodeController {


    @Operation(
        summary = "국가 코드 조회",
        responses = {
            @ApiResponse(responseCode = "200", description = "조회 성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "500", description = "서버 오류",
				content = @Content(schema = @Schema(implementation = ProblemResponseModel.class))),
        }
    )
    @GetMapping("/nation")
    public CommonResponse<Set<NationModel>> nation() {
        return CommonResponse.OK(NationModel.toModel());
    }

}
