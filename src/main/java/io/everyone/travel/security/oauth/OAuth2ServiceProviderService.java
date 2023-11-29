package io.everyone.travel.security.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.everyone.travel.domain.User;
import io.everyone.travel.repository.UserRepository;
import io.everyone.travel.security.oauth.attribute.OAuthAttribute;
import io.everyone.travel.security.oauth.attribute.OAuthMapper;
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

    private final OAuthMapper oAuthMapper;
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        var defaultOAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);

        String clientId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 인증사 정보 기반으로 oAuth 객체로 변환
        var oAuthAttribute = oAuthMapper.of(clientId, attributes);

        log.info("client id: {}, attribute: {}", clientId, oAuthAttribute);

        // 사용자 저장
        User user = saveUser(oAuthAttribute);

        // 사용자 권한을 추가 한다.
        return new OAuth2TravelUser(
            attributes,
            List.of(new SimpleGrantedAuthority("USER")),
            user.getEmail()
        );
    }

    private User saveUser(OAuthAttribute attribute) {
        // 사용자 정보를 저장한다.
        var user = User.builder()
            .authId(attribute.getId())
            .nickname(attribute.getNickname())
            .email(attribute.getEmail())
            .provider(attribute.getProvider())
            .lastLoginAt(LocalDateTime.now())
            .build();

        return userRepository.save(user);
    }

}
