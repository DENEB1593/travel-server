package io.everyone.travel.api.security.oauth.attribute;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.everyone.travel.core.domain.enums.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NaverAttribute implements OAuthAttribute {

    private String resultcode;

    private String message;

    private Response response;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String id;
        private String email;
        private String name;
        private String nickname;
    }


    @Override
    public String getId() {
        return response.getId();
    }

    @Override
    public String getNickname() {
        return response.getNickname();
    }

    @Override
    public String getEmail() {
        return response.getEmail();
    }

    @Override
    public AuthProvider getProvider() {
        return AuthProvider.NAVER;
    }
}
