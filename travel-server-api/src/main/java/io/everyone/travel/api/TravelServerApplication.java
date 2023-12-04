package io.everyone.travel.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "io.everyone.travel.core")
@EnableJpaRepositories(basePackages = "io.everyone.travel.core")
@ConfigurationPropertiesScan(basePackages = "io.everyone.travel")
@SpringBootApplication(scanBasePackages = "io.everyone.travel")
public class TravelServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelServerApplication.class, args);
	}

}
