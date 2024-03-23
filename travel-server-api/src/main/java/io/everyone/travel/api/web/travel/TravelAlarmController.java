package io.everyone.travel.api.web.travel;


import io.everyone.travel.api.exception.model.ProblemResponseModel;
import io.everyone.travel.api.web.CommonResponse;
import io.everyone.travel.api.web.travel.dto.TravelAlarmResponse;
import io.everyone.travel.api.web.travel.mapper.TravelAlarmApiMapper;
import io.everyone.travel.core.domain.travel.service.TravelAlarmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.everyone.travel.api.web.CommonResponse.OK;

@Tag(name = "여행 경보 국가 API")
@Slf4j
@RestController
@RequestMapping("api/travels")
@RequiredArgsConstructor
public class TravelAlarmController {

	private final TravelAlarmService travelAlarmService;

	@Operation(
		summary = "여행 경보 국가 목록 조회",
		responses = {
			@ApiResponse(responseCode = "200", description = "조회 성공", useReturnTypeSchema = true),
			@ApiResponse(responseCode = "404", description = "조회 불가",
				content = @Content( schema = @Schema(implementation = ProblemResponseModel.class)))
		}
	)
	@GetMapping("/alarm-nation")
	public CommonResponse<List<TravelAlarmResponse>> findAll() {
		List<TravelAlarmResponse> alarmResponseList
			= travelAlarmService.findAll().stream()
			.map(TravelAlarmApiMapper::toTravelAlarmResponse)
			.toList();

		return OK(alarmResponseList);
	}



}
