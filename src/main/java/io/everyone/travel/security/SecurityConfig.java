package io.everyone.travel.security;

import io.everyone.travel.security.oauth.OAuth2ServiceProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2ServiceProviderService oAuth2ServiceProviderService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth ->
                auth
                    .requestMatchers(antMatcher("/login")).permitAll()
                    .requestMatchers(antMatcher("/api-docs/**")).permitAll()
                    .requestMatchers(antMatcher("/swagger-ui/**")).permitAll()
                    .requestMatchers(antMatcher("/**")).permitAll() // 개발 중일 때는 permit all
            )
            .oauth2Login(oauth2 ->
                oauth2.loginPage("/auth/login")
                    .userInfoEndpoint(endPoint ->
                        endPoint.userService(oAuth2ServiceProviderService)
                    )
            )
            .build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
            .requestMatchers("/api-docs/**")
            .requestMatchers("/swagger-ui/**");
    }

}
