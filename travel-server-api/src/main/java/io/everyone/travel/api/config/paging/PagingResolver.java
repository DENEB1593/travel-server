package io.everyone.travel.api.config.paging;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 페이징 모델 MethodArgumentResolver
 */
public class PagingResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return PagingModel.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(
        @NonNull MethodParameter parameter,
        ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory) throws Exception {

        String pageParam = webRequest.getParameter("page");
        String sizeParam = webRequest.getParameter("size");

        int page = NumberUtils.toInt(pageParam, 0);
        int size = NumberUtils.toInt(sizeParam, 20);

        return new PagingModel(page, size);
    }
}
