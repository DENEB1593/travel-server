package io.everyone.travel.api.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.zalando.problem.jackson.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.time.ZoneId;
import java.util.TimeZone;

@Configuration
public class ObjectMapperConfig {


    private static final TimeZone ASIA_SEOUL = TimeZone.getTimeZone(ZoneId.of("Asia/Seoul"));


    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder
            .featuresToDisable(
                MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS,
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS,
                DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS,
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
            )
            .modules(
                new JavaTimeModule(),
                new Jdk8Module(),
                new ProblemModule(),
                new ConstraintViolationProblemModule()
            )
            .build()
            .setTimeZone(ASIA_SEOUL);
    }


}
