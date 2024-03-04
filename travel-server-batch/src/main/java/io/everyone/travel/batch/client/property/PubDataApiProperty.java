package io.everyone.travel.batch.client.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api.pub-data")
public record PubDataApiProperty(
    String serviceKey,
    Long pageNo,
    Long numOfRows
) {
}
