package io.everyone.travel.security.oauth.jwt;

import io.everyone.travel.security.oauth.OAuth2TravelUser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest
class JwtServiceTest {

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    JwtService jwtService;

    @Test
    void jwtPropertiesScanTest() {
        assertThat(jwtProperties).isNotNull();
        assertThat(jwtProperties.key()).isNotNull();
        assertThat(jwtProperties.expiration()).isNotNull();
        assertThat(jwtProperties.issuer()).isNotNull();
    }

    @Test
    void jwtTokenGenerateTest() {
        var mock = OAuth2TravelUser.builder()
            .email("some@email.com")
            .build();

        var token = jwtService.generateToken(mock);
        assertThat(token).isNotNull();
    }



}