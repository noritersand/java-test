package laboratory.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ch.qos.logback.classic.ClassicConstants;

/**
 * 로그백 커스텀 MDC 필터
 * 
 * @since 2017-06-01
 * @author fixalot
 */
public class LogbackMdcFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(LogbackMdcFilter.class);
	public static final String REQUEST_METHOD_MDC_KEY = "req.requestMethod";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		generate(request);
		try {
			chain.doFilter(request, response);
		} finally {
			clear();
		}
	}

	private void generate(ServletRequest request) {
		try {
			MDC.put(ClassicConstants.REQUEST_REMOTE_HOST_MDC_KEY, request.getRemoteAddr());
			if (request instanceof HttpServletRequest) {
				HttpServletRequest httpServletRequest = (HttpServletRequest) request;
				MDC.put(ClassicConstants.REQUEST_REQUEST_URI, httpServletRequest.getRequestURI());
//				StringBuffer requestURL = httpServletRequest.getRequestURL();
//				if (requestURL != null) {
//					MDC.put(ClassicConstants.REQUEST_REQUEST_URL, requestURL.toString());
//				}
				MDC.put(ClassicConstants.REQUEST_USER_AGENT_MDC_KEY, httpServletRequest.getHeader("User-Agent"));
				MDC.put(REQUEST_METHOD_MDC_KEY, httpServletRequest.getMethod());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void clear() {
		MDC.remove(ClassicConstants.REQUEST_REMOTE_HOST_MDC_KEY);
		MDC.remove(ClassicConstants.REQUEST_REQUEST_URI);
//		MDC.remove(ClassicConstants.REQUEST_REQUEST_URL);
		MDC.remove(ClassicConstants.REQUEST_USER_AGENT_MDC_KEY);
		MDC.remove(REQUEST_METHOD_MDC_KEY);
	}
}
