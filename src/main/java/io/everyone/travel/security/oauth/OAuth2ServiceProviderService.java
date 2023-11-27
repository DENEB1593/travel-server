package io.everyone.travel.security.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.everyone.travel.domain.User;
import io.everyone.travel.repository.UserRepository;
import io.everyone.travel.security.oauth.attribute.KakaoAttribute;
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
        log.info("client: {}. oauth user attributes: {}", clientId, attributes);

        var kakaoAttribute = objectMapper.convertValue(attributes, KakaoAttribute.class);

        // 인증정보를 OAuthUser로 변환한다.
        String authId = kakaoAttribute.getId();
        String nickname = kakaoAttribute.getProperties().getNickname();
        String email = kakaoAttribute.getKakaoAccount().getEmail();

        // 사용자 정보를 저장한다.
        User user = User.builder()
            .authId(authId)
            .nickname(nickname)
            .email(email)
            .provider("KAKAO")
            .lastLoginAt(LocalDateTime.now())
            .build();

        userRepository.save(user);

        // 사용자 권한을 추가 한다.
        return new OAuth2TravelUser(
            attributes,
            List.of(new SimpleGrantedAuthority("USER")),
            nickname,
            email
        );
    }

}
