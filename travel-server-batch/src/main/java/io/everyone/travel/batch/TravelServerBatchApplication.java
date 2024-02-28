package io.everyone.travel.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelServerBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelServerBatchApplication.class, args);
    }
}
