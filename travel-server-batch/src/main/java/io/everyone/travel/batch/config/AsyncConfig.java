package io.everyone.travel.batch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ForkJoinPool;

@EnableAsync
@Configuration
public class AsyncConfig {

    @Bean
    public ForkJoinPool defaultAsyncPool() {
        return new ForkJoinPool(10);
    }

}
