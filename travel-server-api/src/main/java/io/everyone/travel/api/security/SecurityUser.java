package io.everyone.travel.api.security;

import io.everyone.travel.core.domain.user.entity.User;
import io.everyone.travel.core.domain.user.enums.AuthProvider;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SecurityUser {

    String id;

    String nickname;

    String email;

    AuthProvider provider;

    public static SecurityUser of(User user) {
        return SecurityUser.builder()
            .id(user.getAuthId())
            .nickname(user.getNickname())
            .email(user.getEmail())
            .provider(user.getProvider())
            .build();
    }


}
