package io.everyone.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan(basePackages = "io.everyone.travel")
@SpringBootApplication
public class TravelServerBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelServerBatchApplication.class, args);
    }
}
