package io.everyone.travel.controller;

import io.everyone.travel.controller.dto.TravelWriteRequest;
import io.everyone.travel.domain.Travel;
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

    @PostMapping
    public ResponseEntity<Travel> save(
            @RequestBody TravelWriteRequest travelWriteRequest) {
        Travel travel = travelService.save(travelWriteRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(travel);
    }

}
