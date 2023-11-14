package io.everyone.travel.config;

import io.everyone.travel.config.page.PageArgumentHandlerMethodArgumentResolver;
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
