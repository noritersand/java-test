package lab.servlet.core.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HTMLTagFilter implements Filter {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(HTMLTagFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new HTMLTagFilterRequestWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {
        // do nothing
    }

}
