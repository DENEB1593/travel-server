package io.everyone.travel.api.config;

import io.everyone.travel.api.config.paging.PagingResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    PagingResolver pageArgumentHandlerMethodArgumentResolver() {
        return new PagingResolver();
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(pageArgumentHandlerMethodArgumentResolver());

    }
}
