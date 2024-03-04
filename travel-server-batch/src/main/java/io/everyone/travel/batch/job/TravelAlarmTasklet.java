package io.everyone.travel.batch.job;

import io.everyone.travel.batch.client.PubDataApiClient;
import io.everyone.travel.batch.client.response.TravelAlarmResponse;
import io.everyone.travel.core.domain.travel.entity.TravelAlarm;
import io.everyone.travel.core.domain.travel.enums.Nation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@StepScope
public class TravelAlarmTasklet implements Tasklet {

    private final PubDataApiClient pubDataClient;

    public TravelAlarmTasklet(PubDataApiClient pubDataClient) {
        this.pubDataClient = pubDataClient;
    }


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        log.info("travel-alarm-batch started");
        // read
        TravelAlarmResponse travelAlarmResponse = pubDataClient.getTravelAlarmList();
        if (travelAlarmResponse.getResultCode() != 0) {
            throw new RuntimeException("response code error");
        }

        // process
        travelAlarmResponse.getData()
            .stream()
            .map(it -> TravelAlarm.builder()
                .alarmLvl(it.getAlarmLvl())
                .nation(Nation.typeOf(it.getCountryIsoAlp2()))
                .writtenDt(it.getWrittenDt())
                .regionTy(it.getRegionTy())
                .remark(it.getRemark())
                .build()
            )
            .forEach(it -> {
                log.info("travel alarm: {}", it);
                // TODO save
            });


        // write

        return RepeatStatus.FINISHED;
    }
}
