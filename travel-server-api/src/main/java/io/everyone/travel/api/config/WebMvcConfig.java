package io.everyone.travel.api.config;

import io.everyone.travel.api.config.pagination.PageArgumentHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    PageArgumentHandlerMethodArgumentResolver pageArgumentHandlerMethodArgumentResolver() {
        return new PageArgumentHandlerMethodArgumentResolver();
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(pageArgumentHandlerMethodArgumentResolver());

    }
}
