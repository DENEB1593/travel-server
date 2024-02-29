package io.everyone.travel.batch.job.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SampleJobConfig {

    private static final String JOB_NAME = "sampleJob";
    private static final String STEP_NAME = "sampleStep";
    private static final int CHUNK_SIZE = 1;

    private final JobRepository jobRepository;

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
    }
}
