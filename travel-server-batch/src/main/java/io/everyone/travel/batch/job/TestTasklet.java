package io.everyone.travel.batch.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@StepScope
public class TestTasklet implements Tasklet {
    // 차라리 Tasklet에 몰아서 하는게 좋은 듯
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // read

        // process

        // write

        return RepeatStatus.FINISHED;
    }
}
