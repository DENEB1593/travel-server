package io.everyone.travel.security.oauth.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
    String key,
    Long expiration,
    String issuer
) {
}
