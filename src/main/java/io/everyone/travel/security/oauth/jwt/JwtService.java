package io.everyone.travel.security.oauth.jwt;

import io.everyone.travel.security.oauth.OAuth2TravelUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    public String generateToken(OAuth2TravelUser oAuth2TravelUser) {
        return Jwts.builder()
            .issuer(jwtProperties.issuer())
            .subject(oAuth2TravelUser.getEmail())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + jwtProperties.expiration()))
            .signWith(generateKey())
            .compact();
    }

    private Key generateKey() {
        var keyBytes = Decoders.BASE64.decode(jwtProperties.key());
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
