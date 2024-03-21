package io.everyone.travel.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TravelAlarmScheduler {

    private final ThreadPoolTaskScheduler taskScheduler;
    private final TravelAlarmTasklet travelAlarmTasklet;


    @Scheduled(fixedRate = 10_000) // Run every 5 seconds
    public void executeTasklet() {
        taskScheduler.execute(() -> {
            try {
                travelAlarmTasklet.execute(null, null); // Pass appropriate parameters or context if needed
            } catch (Exception e) {
                log.error("여행경보 정보 수집 배치 오류 발생 - message: {}", e.getMessage(), e);
            }
        });
    }

}
