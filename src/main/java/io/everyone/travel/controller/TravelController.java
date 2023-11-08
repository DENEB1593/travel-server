package io.everyone.travel.controller;

import io.everyone.travel.controller.dto.TravelWriteRequest;
import io.everyone.travel.controller.dto.TravelWriteResponse;
import io.everyone.travel.domain.Travel;
import io.everyone.travel.mapper.TravelMapper;
import io.everyone.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travels")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;


//    @Operation(
//            summary = "temp",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "OK"),
//                    @ApiResponse(
//                            responseCode = "500",
//                            description = "서버 에러",
//                            content = @Content(
//                                    mediaType = "application/json",
//                                    schema = @Schema(implementation = TempResponse.class))),
//            }
//    )
    @PostMapping
    public ResponseEntity<TravelWriteResponse> save(
            @RequestBody TravelWriteRequest travelWriteRequest) {
        Travel travel = travelService.save(travelWriteRequest);
        TravelWriteResponse response = TravelMapper.toResponse(travel);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
