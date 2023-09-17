package testbed.servlet.core.filter;

import ch.qos.logback.classic.ClassicConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 로그백 커스텀 MDC 필터
 *
 * @author fixalot
 * @since 2017-06-01
 */
@Slf4j
public class LogbackMdcFilter implements Filter {
    public static final String REQUEST_METHOD_MDC_KEY = "req.requestMethod";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void destroy() {
        // do nothing
    }

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
            if (request instanceof HttpServletRequest httpServletRequest) {
                MDC.put(ClassicConstants.REQUEST_REQUEST_URI, httpServletRequest.getRequestURI());
//				StringBuffer requestURL = httpServletRequest.getRequestURL();
//				if (requestURL != null) {
//					MDC.put(ClassicConstants.REQUEST_REQUEST_URL, requestURL.toString());
//				}
                MDC.put(ClassicConstants.REQUEST_USER_AGENT_MDC_KEY, httpServletRequest.getHeader("User-Agent"));
                MDC.put(REQUEST_METHOD_MDC_KEY, httpServletRequest.getMethod());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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
