package io.everyone.travel.batch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ForkJoinPool;

@Configuration
public class AsyncConfig {

    @Bean
    public ForkJoinPool defaultAsyncPool() {
        return new ForkJoinPool(10);
    }

}
