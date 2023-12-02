package io.everyone.travel.security.oauth.jwt;

import io.everyone.travel.security.oauth.OAuth2UserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@Disabled
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
        var mock = OAuth2UserDetails.builder()
            .email("some@email.com")
            .build();

        var token = jwtService.generateToken(mock);
        assertThat(token).isNotNull();
    }

    @Test
    void extractTokenTest() {
        var mock = OAuth2UserDetails.builder()
            .email("my@email.net")
            .build();

        String token = jwtService.generateToken(mock);

        assertThat(token).isNotNull();

        var claim = jwtService.extractToken(token);
        String subject = claim.getSubject();
        System.out.println(subject);

    }



}