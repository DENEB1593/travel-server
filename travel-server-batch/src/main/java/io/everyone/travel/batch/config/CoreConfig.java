package io.everyone.travel.batch.config;

import io.everyone.travel.core.config.JpaConfig;
import io.everyone.travel.core.config.ObjectMapperConfig;
import io.everyone.travel.core.config.S3Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
    JpaConfig.class,
    ObjectMapperConfig.class,
    S3Config.class
})
@Configuration
public class CoreConfig {
}
