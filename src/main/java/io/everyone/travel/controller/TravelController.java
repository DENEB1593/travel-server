package io.everyone.travel.controller;

import io.everyone.travel.controller.common.CommonResponse;
import io.everyone.travel.controller.dto.TravelView;
import io.everyone.travel.controller.dto.TravelWriteRequest;
import io.everyone.travel.controller.dto.TravelWriteResponse;
import io.everyone.travel.exception.NotFoundException;
import io.everyone.travel.mapper.TravelMapper;
import io.everyone.travel.service.TravelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "여행 API")
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
    ) {
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
                            description = "조회 불가"
                    )
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


}
