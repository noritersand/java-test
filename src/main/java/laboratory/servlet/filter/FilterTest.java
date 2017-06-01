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

public class FilterTest implements Filter {
//	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(FilterTest.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("initialize " + this.getClass());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.debug("approching " + this.getClass());
		if (request instanceof HttpServletRequest) {
//			HttpServletRequest req = (HttpServletRequest) request;
//			log.debug(req.getRequestURI());
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		log.debug("destroy " + this.getClass());
	}
}
