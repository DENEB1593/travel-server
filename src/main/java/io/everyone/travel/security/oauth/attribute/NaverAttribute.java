package io.everyone.travel.security.oauth.attribute;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NaverAttribute implements OAuthAttribute {

    private String resultcode;

    private String message;

    private Response response;

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

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String id;
        private String email;
        private String name;
        private String nickname;
    }


}
