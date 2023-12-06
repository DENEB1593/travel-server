package io.everyone.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan(basePackages = "io.everyone.travel")
@SpringBootApplication
public class TravelServerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelServerApiApplication.class, args);
	}

}
