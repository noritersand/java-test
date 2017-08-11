package laboratory.servlet.mvc.finder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @since 2017-08-09
 * @author fixalot
 */
public interface MethodFinder {
	public Object[] findMethod(HttpServletRequest request, HttpServletResponse response);
}
