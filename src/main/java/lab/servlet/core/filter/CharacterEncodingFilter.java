package lab.servlet.core.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 기본 문자인코딩 설정 필터. 모든 필터/인터셉터보다 가장 먼저 진입되도록 설정해야 함.
 *
 * @author fixalot
 * @since 2017-06-05
 */
@Slf4j
public class CharacterEncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
//		request.setCharacterEncoding(StandardCharsets.ISO_8859_1.toString());
        // server.xml에 URIEncoding 항목을 지정하지 않고, request.setCharacterEncoding을 명시적으로 호출하지 않으면 기본 케릭터 셋은 ISO-8859-1로 설정된다.
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
