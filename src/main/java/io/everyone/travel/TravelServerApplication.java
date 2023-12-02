package io.everyone.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "io.everyone.travel")
public class TravelServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelServerApplication.class, args);
	}

}
