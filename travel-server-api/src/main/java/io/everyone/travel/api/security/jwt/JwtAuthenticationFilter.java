package io.everyone.travel.api.security.jwt;

import io.everyone.travel.api.security.Role;
import io.everyone.travel.api.security.SecurityUser;
import io.everyone.travel.core.domain.user.entity.User;
import io.everyone.travel.core.exception.NotFoundException;
import io.everyone.travel.core.domain.user.repo.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nonnull;
import java.io.IOException;

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
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = header.substring(7);
        String email =  jwtService.extract(jwt);
        SecurityContext context = SecurityContextHolder.getContext();

        // 토큰 유효성 검사(만료 시간, 사용자 존재 등)
        if (StringUtils.hasText(email) || context.getAuthentication() == null) {
            User user = userRepository
                .findByEmail(email)
                .orElseThrow(NotFoundException::forUser);

            SecurityUser securityUser = SecurityUser.of(user);

            if (jwtService.isTokenValid(jwt, user.getEmail())) {
                // 인증정보 저장 처리
                var authentication
                    = new UsernamePasswordAuthenticationToken(
                        securityUser,null, AuthorityUtils.createAuthorityList(Role.USER.name()));

                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
            }

        }

        filterChain.doFilter(request, response);
    }
}
