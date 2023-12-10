package io.everyone.travel.api.security;

import io.everyone.travel.api.security.jwt.JwtAuthenticationFilter;
import io.everyone.travel.api.security.oauth.OAuth2ServiceProviderService;
import io.everyone.travel.api.security.oauth.OAuth2TravelAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2ServiceProviderService oAuth2ServiceProviderService;
    private final OAuth2TravelAuthenticationSuccessHandler oAuthSuccessHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth ->
                auth
                    .requestMatchers(antMatcher("/auth/login")).permitAll()
                    .requestMatchers(antMatcher("/api-docs/**")).permitAll()
                    .requestMatchers(antMatcher("/swagger-ui/**")).permitAll()
                    .requestMatchers(antMatcher("/api/**")).hasRole(Role.USER.name()) // 모든 API에 대한 접근은 회원들만 할 수 있도록
                    .anyRequest().permitAll()
            )
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .oauth2Login(oauth2 ->
                oauth2.loginPage("/auth/login")
                    .userInfoEndpoint(endPoint ->
                        endPoint.userService(oAuth2ServiceProviderService)
                    )
                    .successHandler(oAuthSuccessHandler)
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
            .requestMatchers(antMatcher("/api-docs/**"))
            .requestMatchers(antMatcher("/swagger-ui/**"))
            .requestMatchers(antMatcher("/static/**"))
            .requestMatchers(antMatcher("/favicon.ico"))
            .requestMatchers(antMatcher("/templates/**"));
    }

}
