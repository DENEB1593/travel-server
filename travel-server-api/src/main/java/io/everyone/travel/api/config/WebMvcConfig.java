package io.everyone.travel.api.config;

import io.everyone.travel.api.config.paging.PagingResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

	private final MdcLoggingInterceptor mdcLoggingInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(mdcLoggingInterceptor);
	}

	PagingResolver pageArgumentHandlerMethodArgumentResolver() {
        return new PagingResolver();
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(pageArgumentHandlerMethodArgumentResolver());

    }
}
