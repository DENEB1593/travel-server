package io.everyone.travel.security.oauth.attribute;

import io.everyone.travel.domain.enums.AuthProvider;

public interface OAuthAttribute {
    String getId();

    String getNickname();

    String getEmail();

    AuthProvider getProvider();
}
