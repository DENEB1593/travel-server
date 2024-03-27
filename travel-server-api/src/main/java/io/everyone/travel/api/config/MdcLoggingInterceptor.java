package io.everyone.travel.api.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Slf4j
@Component
public class MdcLoggingInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


		/**
		 * request에서 REQUEST_URI, METHOD, ParamMap, HOST, IP, USER_AGENT,
		 * REMOTE_ADDR, X_FORWARDED_FOR, REFERER
		 **/
		String requestUri = request.getRequestURI();
		String method = request.getMethod();
		Map<String, String[]> param = request.getParameterMap();
		String host = request.getRemoteHost();
		String ipAddress = request.getRemoteAddr();
		String userAgent = request.getHeader("User-Agent");
		String xForwardedFor = request.getHeader("X_FORWARDED_FOR");

		MDC.put("requestUri", requestUri);
		MDC.put("method", method);
		MDC.put("param", param.toString());
		MDC.put("host", host);
		MDC.put("ipAddress", ipAddress);
		MDC.put("userAgent", userAgent);
		MDC.put("xForwardedFor", xForwardedFor);

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		MDC.clear();
	}
}
