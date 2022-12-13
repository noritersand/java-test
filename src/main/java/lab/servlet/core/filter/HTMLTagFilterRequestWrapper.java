package lab.servlet.core.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 얘가 뭐하는 놈이냐면, 너무 오래되서 기억이 잘 안나는데 아마도 {@link HttpServletRequest}의 메서드 구현체를 교체하는 놈이었을 것이여.
 *
 * @author noritersand
 * @since 2021-02-16
 */
@Slf4j
public class HTMLTagFilterRequestWrapper extends HttpServletRequestWrapper {

    public HTMLTagFilterRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        log.debug("유후후!!");
        return values;
    }

    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        log.debug("유후!");
        return value;
    }
}
