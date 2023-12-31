package io.everyone.travel.api.security.oauth.attribute;

import io.everyone.travel.core.domain.user.enums.AuthProvider;

public interface OAuthAttribute {
    String getId();

    String getNickname();

    String getEmail();

    AuthProvider getProvider();
}
