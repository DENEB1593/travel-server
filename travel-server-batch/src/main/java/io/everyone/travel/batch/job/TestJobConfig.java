package io.everyone.travel.batch.job;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TestJobConfig {
/*
    private static final String JOB_NAME = "sampleJob";
    private static final String STEP_NAME = "sampleStep";
    private static final int CHUNK_SIZE = 1;

    private final SomeRepository someRepository;

    @Bean(JOB_NAME)
    public Job sampleJob() {
        return new JobBuilder(JOB_NAME, jobRepository)
            .start(
                new StepBuilder(STEP_NAME, jobRepository)
                    .<String, String>chunk(
                        CHUNK_SIZE,
                        new ResourcelessTransactionManager()
                    )
                    .reader(new SampleReader())
                    .processor(new SampleProcessor())
                    .writer(new SampleWriter())
                    .build()
            )
            .build();
    }*/
}
