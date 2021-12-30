package lab.servlet.core.finder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @since 2017-08-09
 * @author fixalot
 */
public interface MethodFinder {
	public Object[] findMethod(List<Object> instanceList, HttpServletRequest request, HttpServletResponse response);
}
