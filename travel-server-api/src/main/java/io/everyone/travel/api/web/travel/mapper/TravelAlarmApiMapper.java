package io.everyone.travel.api.web.travel.mapper;

import io.everyone.travel.api.web.travel.dto.NationModel;
import io.everyone.travel.api.web.travel.dto.TravelAlarmResponse;
import io.everyone.travel.core.domain.travel.entity.TravelAlarm;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TravelAlarmApiMapper {

	public TravelAlarmResponse toTravelAlarmResponse(TravelAlarm travelAlarm) {
		return TravelAlarmResponse.builder()
			.nation(NationModel.of(travelAlarm.getNation()))
			.alarmLvl(travelAlarm.getAlarmLvl())
			.regionTy(travelAlarm.getRegionTy())
			.remark(travelAlarm.getRemark())
			.writtenDt(travelAlarm.getWrittenDt())
			.build();
	}

}
