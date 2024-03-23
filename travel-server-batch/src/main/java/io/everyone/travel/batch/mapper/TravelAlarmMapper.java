package io.everyone.travel.batch.mapper;

import io.everyone.travel.batch.client.response.TravelAlarmResponse;
import io.everyone.travel.core.domain.travel.entity.TravelAlarm;
import io.everyone.travel.core.domain.travel.enums.Nation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TravelAlarmMapper {

	public TravelAlarm toTravelAlarm(TravelAlarmResponse.TravelAlarmData data) {
		return TravelAlarm.builder()
			.nation(Nation.typeOf(data.getRegionTy()))
			.alarmLvl(data.getAlarmLvl())
			.nation(Nation.typeOf(data.getCountryIsoAlp2()))
			.writtenDt(data.getWrittenDt())
			.regionTy(data.getRegionTy())
			.remark(data.getRemark())
			.build();
	}
}
