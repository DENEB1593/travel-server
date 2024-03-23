package io.everyone.travel.api.web.travel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

/**
 *     "createdAt": "2024-03-23T11:20:12.208409",
 *       "modifiedAt": "2024-03-23T11:20:12.208409",
 *       "deletedAt": null,
 *       "nation": {
 *         "code": "CM",
 *         "kr": "카메룬",
 *         "eng": "CAMEROON"
 *       },
 *       "alarmLvl": "3",
 *       "regionTy": "일부",
 *       "remark": "노르드 주, 아다모와 주, 북서부 주, 남서부 주, 최북부 주",
 *       "writtenDt": "2022-11-29"
 */
@Value
@Builder
public class TravelAlarmResponse {


	@Schema(description = "국가 코드", example = "CM")
	NationModel nation;

	@Schema(description = "경보 단계", example = "3")
	String alarmLvl;

	@Schema(description = "경보 지역", example = "일부")
	String regionTy;

	@Schema(description = "비고", example = "노르드 주, 아다모와 주, 북서부 주, 남서부 주, 최북부 주")
	String remark;

	@Schema(description = "갱신일자", example = "2022-11-29")
	LocalDate writtenDt;

}
