package io.everyone.travel.api.security.oauth.attribute;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.everyone.travel.core.domain.user.enums.AuthProvider;
import io.everyone.travel.core.util.EnumUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OAuthMapper {

    private final ObjectMapper objectMapper;

    /**
     * OAuthAttribute 객체로 변환
     */
    public OAuthAttribute of(String clientId, Map<String, Object> attributes) {
        var provider = EnumUtils.byEnumName(AuthProvider.class, clientId);
        Objects.requireNonNull(provider);
        return switch (provider) {
            case KAKAO -> objectMapper.convertValue(attributes, KakaoAttribute.class);
            case NAVER -> objectMapper.convertValue(attributes, NaverAttribute.class);
        };

    }

}
