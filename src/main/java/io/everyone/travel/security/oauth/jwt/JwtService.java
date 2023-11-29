package io.everyone.travel.security.oauth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    public String generateToken(OAuth2User oAuth2User) {
        return Jwts.builder()
            .issuer(jwtProperties.issuer())
            .subject(oAuth2User.getName())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + jwtProperties.expiration()))
            .signWith(generateKey())
            .compact();
    }

    private SecretKey generateKey() {
        var keyBytes = Decoders.BASE64.decode(jwtProperties.key());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractToken(String token) {
        return Jwts
            .parser()
            .verifyWith(generateKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }


    public String extract(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final var claims = extractToken(token);
        return claimsResolvers.apply(claims);
    }

}
