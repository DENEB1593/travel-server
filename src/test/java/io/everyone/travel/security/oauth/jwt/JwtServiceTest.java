package io.everyone.travel.security.oauth.jwt;

import io.everyone.travel.security.jwt.JwtProperties;
import io.everyone.travel.security.jwt.JwtService;
import io.everyone.travel.security.oauth.OAuth2UserDetails;
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
        assertThat(claim).isNotNull();
        assertThat(claim.getSubject()).isEqualTo(mock.getEmail());
    }

    @Test
    void tokenValidTests() {
        // naver sample
        String givenToken = "eyJhbGciOiJIUzM4NCJ9.eyJpc3MiOiJ0cmF2ZWwtc2VydmVyLWF1dGgiLCJzdWIiOiJsZWVrYW5nd29vbjFAbmF2ZXIuY29tIiwiaWF0IjoxNzAxNDk0NzA4LCJleHAiOjE3MDE0OTgzMDh9._FdTCDD3R6vR1WYt0vp5xmhNmd5-M2ruWLyQ62CnlnrPSPmFTxotalZxZRgBPQKk";
        String originEmail = "leekangwoon1@naver.com";
        assertThat(jwtService.isTokenValid(givenToken, originEmail)).isTrue();

        // kakao sample
        String givenToken2 = "eyJhbGciOiJIUzM4NCJ9.eyJpc3MiOiJ0cmF2ZWwtc2VydmVyLWF1dGgiLCJzdWIiOiJsZWVrYW5nd29vbkBuYXRlLmNvbSIsImlhdCI6MTcwMTQ5NTAxNCwiZXhwIjoxNzAxNDk4NjE0fQ.s2J4s35XVZdCMxXgqZcFzJLLFEeiTgSLRtG9q17opj_vTbzUo_JWsQT4FBmVoeoq";
        String originEmail2 = "leekangwoon@nate.com";
        assertThat(jwtService.isTokenValid(givenToken2, originEmail2)).isTrue();
    }



}