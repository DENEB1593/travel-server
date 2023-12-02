package io.everyone.travel.security.oauth.attribute;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.everyone.travel.domain.enums.AuthProvider;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoAttribute implements OAuthAttribute {

    @Getter
    private String id;

    private LocalDateTime connectedAt;

    private Properties properties;

    private KakaoAccount kakaoAccount;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Properties {
        private String nickname;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KakaoAccount {
        private Boolean profileNicknameNeedsAgreement;
        private Boolean hasEmail;
        private Boolean emailNeeds;
        private Boolean isEmailValid;
        private Boolean isEmailVerified;
        private String email;

    }


    @Override
    public String getNickname() {
        return properties.getNickname();
    }

    @Override
    public String getEmail() {
        return kakaoAccount.getEmail();
    }

    @Override
    public AuthProvider getProvider() {
        return AuthProvider.KAKAO;
    }

}
