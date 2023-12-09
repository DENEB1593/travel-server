package io.everyone.travel.api.config.xss;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class XssFilter extends OncePerRequestFilter {

    /**
     * XSS 공격 방지용 Filter 정의
     * 참조: HtmlSupports
     *
     */
    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        doFilter(new XssPreventRequestWrapper(request), response, filterChain);
    }
}
