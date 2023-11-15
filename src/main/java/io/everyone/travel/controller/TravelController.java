package io.everyone.travel.controller;

import io.everyone.travel.config.page.PageModel;
import io.everyone.travel.controller.common.CommonResponse;
import io.everyone.travel.controller.dto.TravelView;
import io.everyone.travel.controller.dto.TravelWriteRequest;
import io.everyone.travel.controller.dto.TravelWriteResponse;
import io.everyone.travel.exception.NotFoundException;
import io.everyone.travel.exception.model.ProblemResponseModel;
import io.everyone.travel.mapper.TravelMapper;
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
    @PostMapping
    public CommonResponse<TravelWriteResponse> save(
        @RequestBody @Valid TravelWriteRequest travelWriteRequest
        //TODO 이미지 업로드 요청 구현
    ) {

        //TODO 여행 저장 후 이미지 업로드 진행
        return CommonResponse.OK(
            TravelMapper.toResponse(
                travelService.save(travelWriteRequest))
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
    @GetMapping("/{id}")
    public CommonResponse<TravelView> find(
        @PathVariable Long id
    ) {
        return CommonResponse.OK(
            travelService.findById(id)
                .map(TravelMapper::toView)
                .orElseThrow(() -> new NotFoundException(String.format("travel not found with id [%d]", id)))
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
    @Parameters ({
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


}
