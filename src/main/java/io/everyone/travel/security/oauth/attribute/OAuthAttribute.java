package io.everyone.travel.security.oauth.attribute;

import io.everyone.travel.security.oauth.OAuthProvider;

public interface OAuthAttribute {
    String getId();

    String getNickname();

    String getEmail();

    OAuthProvider getProvider();
}
