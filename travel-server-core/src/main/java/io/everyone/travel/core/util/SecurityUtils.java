package io.everyone.travel.core.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@UtilityClass
public class SecurityUtils {

    public UserDetails createUserDetails(String username, String role) {
        return User.builder()
            .username(username)
            .roles(role)
            .disabled(false)
            .accountExpired(false)
            .accountLocked(false)
            .build();
    }

}
