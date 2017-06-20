package laboratory.servlet.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 기본 문자인코딩 설정 필터. 
 * 모든 필터/인터셉터보다 가장 먼저 진입되도록 설정해야 함.
 * 
 * @since 2017-06-05
 * @author fixalot
 */
public class CharacterEncodingFilter implements Filter {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CharacterEncodingFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// do nothing
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
//		request.setCharacterEncoding(StandardCharsets.ISO_8859_1.toString());
		// server.xml에 URIEncoding 항목을 지정하지 않고, request.setCharacterEncoding를 사용하지 않으면 기본 케릭터 셋은 ISO-8859-1로 설정된다.
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// do nothing
	}
}
