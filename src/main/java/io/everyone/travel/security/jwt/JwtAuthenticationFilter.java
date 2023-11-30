package io.everyone.travel.security.jwt;

import io.everyone.travel.domain.User;
import io.everyone.travel.exception.NotFoundException;
import io.everyone.travel.repository.UserRepository;
import io.everyone.travel.security.oauth.OAuth2TravelUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
        @Nonnull HttpServletRequest request,
        @Nonnull HttpServletResponse response,
        @Nonnull FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (!StringUtils.hasText(header) || !header.startsWith("Bearer ")) {
            log.warn("no authentication header : {}", header);
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = header.substring(7);
        log.debug("jwt authentication - {}", jwt);
        String email =  jwtService.extract(jwt);
        SecurityContext context = SecurityContextHolder.getContext();

        if (StringUtils.hasText(email) || context.getAuthentication() == null) {
            // 토큰 유효성 검사( 만료시간, 사용자 존재 등)

            User user = userRepository
                .findByEmail(email)
                .orElseThrow(NotFoundException::forUser);

            var oAuth2User = OAuth2TravelUser.builder()
                .attributes(new HashMap<>())
                .authorities(List.of(new SimpleGrantedAuthority("USER")))
                .email(email)
                .build();

            var authentication
                = new UsernamePasswordAuthenticationToken(oAuth2User, null, oAuth2User.getAuthorities());

            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
        }

        filterChain.doFilter(request, response);
    }
}
