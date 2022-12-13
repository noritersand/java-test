package lab.servlet.core.filter;

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
