package io.everyone.travel.batch.config;

import io.micrometer.context.ContextExecutorService;
import io.micrometer.context.ContextSnapshotFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ExecutorConfig {

    @Bean
    public Executor defaultExecutor() {
        var threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors() * 2);
        threadPoolExecutor.setThreadNamePrefix("travel-batch-executor");
        threadPoolExecutor.initialize();

        return ContextExecutorService.wrap(
            threadPoolExecutor.getThreadPoolExecutor(),
            ContextSnapshotFactory.builder().build()::captureAll
        );
    }
}
