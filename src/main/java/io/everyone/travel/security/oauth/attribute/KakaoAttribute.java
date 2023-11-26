package io.everyone.travel.security.oauth.attribute;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoAttribute {

    private Long id;

    private LocalDateTime connectedAt;

    private Properties properties;

    private KakaoAccount kakaoAccount;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Properties {
        private String nickname;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class KakaoAccount {
        private Boolean profileNicknameNeedsAgreement;
        private Boolean hasEmail;
        private Boolean emailNeeds;
        private Boolean isEmailValid;
        private Boolean isEmailVerified;
        private String email;

    }


}
