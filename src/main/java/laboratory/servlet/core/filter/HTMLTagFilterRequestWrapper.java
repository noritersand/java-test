package laboratory.servlet.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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