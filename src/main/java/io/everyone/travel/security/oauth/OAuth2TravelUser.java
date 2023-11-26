package io.everyone.travel.security.oauth;

import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Value
public class OAuth2TravelUser implements OAuth2User {

    Map<String, Object> attributes;
    List<GrantedAuthority> authorities;
    String name;
    String email;


    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
