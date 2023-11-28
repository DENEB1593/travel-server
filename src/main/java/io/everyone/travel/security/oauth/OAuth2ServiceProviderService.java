package io.everyone.travel.security.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.everyone.travel.domain.User;
import io.everyone.travel.repository.UserRepository;
import io.everyone.travel.security.oauth.attribute.KakaoAttribute;
import io.everyone.travel.security.oauth.attribute.NaverAttribute;
import io.everyone.travel.security.oauth.attribute.OAuthAttribute;
import io.everyone.travel.util.EnumSupports;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2ServiceProviderService
    implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("oauth request: {}", userRequest);

        var defaultOAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);

        String clientId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        // 분기 처리
        OAuthProvider provider = EnumSupports.byEnumName(OAuthProvider.class, clientId);

        var attribute = switch (provider) {
            case KAKAO -> objectMapper.convertValue(attributes, KakaoAttribute.class);
            case NAVER -> objectMapper.convertValue(attributes, NaverAttribute.class);
        };

        log.info("client id: {}, attribute: {}", clientId, attribute);

        // 인증정보를 OAuthUser로 변환한다.
        String authId = attribute.getId();
        String nickname = attribute.getNickname();
        String email = attribute.getEmail();

        // 사용자 정보를 저장한다.
        User user = User.builder()
            .authId(authId)
            .nickname(nickname)
            .email(email)
            .provider(provider)
            .lastLoginAt(LocalDateTime.now())
            .build();

        userRepository.save(user);

        // 사용자 권한을 추가 한다.
        return new OAuth2TravelUser(
            attributes,
            List.of(new SimpleGrantedAuthority("USER")),
            email
        );
    }

}
