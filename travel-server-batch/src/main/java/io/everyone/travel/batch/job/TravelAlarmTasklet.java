package io.everyone.travel.batch.job;

import io.everyone.travel.batch.client.PubDataApiClient;
import io.everyone.travel.core.domain.travel.entity.TravelAlarm;
import io.everyone.travel.core.domain.travel.enums.Nation;
import io.everyone.travel.core.domain.travel.repo.TravelAlarmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TravelAlarmTasklet implements Tasklet {

    private final PubDataApiClient pubDataClient;
	private final TravelAlarmRepository travelAlarmRepository;


    public TravelAlarmTasklet(
		PubDataApiClient pubDataClient,
		TravelAlarmRepository travelAlarmRepository) {
        this.pubDataClient = pubDataClient;
        this.travelAlarmRepository = travelAlarmRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		List<TravelAlarm> travelAlarmList = pubDataClient.getTravelAlarmList()
			.getData()
			.stream()
			.filter(it -> Nation.typeOf(it.getCountryIsoAlp2()) != Nation.UNKNOWN)
			.map(it -> TravelAlarm.builder()
				.nation(Nation.typeOf(it.getRegionTy()))
				.alarmLvl(it.getAlarmLvl())
				.nation(Nation.typeOf(it.getCountryIsoAlp2()))
				.writtenDt(it.getWrittenDt())
				.regionTy(it.getRegionTy())
				.remark(it.getRemark())
				.build()
			)
			.toList();


		// write
		travelAlarmRepository.saveAll(travelAlarmList);

		log.info("travel alarm list saved success - count:{}", travelAlarmList.size());
        return RepeatStatus.FINISHED;
    }
}
