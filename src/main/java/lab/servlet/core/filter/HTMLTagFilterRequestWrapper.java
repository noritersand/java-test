package lab.servlet.core.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 얘가 뭐하는 놈이냐면, 너무 오래되서 기억이 잘 안나는데 아마도 {@link HttpServletRequest}의 메서드 구현체를 교체하는 놈이었을 것이여.
 *
 * @author noritersand
 * @since 2021-02-16
 */
public class HTMLTagFilterRequestWrapper extends HttpServletRequestWrapper {
    private static final Logger logger = LoggerFactory.getLogger(HTMLTagFilterRequestWrapper.class);

    public HTMLTagFilterRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        logger.debug("유후후!!");
        return values;
    }

    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        logger.debug("유후!");
        return value;
    }
}
