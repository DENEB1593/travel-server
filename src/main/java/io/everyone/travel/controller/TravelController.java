package io.everyone.travel.controller;

import io.everyone.travel.controller.dto.TravelView;
import io.everyone.travel.controller.dto.TravelWriteRequest;
import io.everyone.travel.controller.dto.TravelWriteResponse;
import io.everyone.travel.domain.Travel;
import io.everyone.travel.mapper.TravelMapper;
import io.everyone.travel.service.TravelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "여행 정보 API")
@RestController
@RequestMapping("/travels")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;


    @Operation(
            summary = "여행 정보 저장",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "CREATED",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TravelWriteResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<TravelWriteResponse> save(
            @RequestBody TravelWriteRequest travelWriteRequest) {
        TravelWriteResponse response = TravelMapper.toResponse(
                travelService.save(travelWriteRequest));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(
            summary = "여행 정보 단건 조회",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TravelView.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<TravelView> find(
            @PathVariable Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        travelService.findById(id)
                                .map(TravelMapper::toView)
                        .orElseThrow(RuntimeException::new) // 커스텀 예외는 추후 개발, 우선은 Runtime 으로
                );
    }


}
