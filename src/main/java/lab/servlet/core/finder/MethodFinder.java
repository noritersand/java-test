package lab.servlet.core.finder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author fixalot
 * @since 2017-08-09
 */
public interface MethodFinder {
    Object[] findMethod(List<Object> instanceList, HttpServletRequest request, HttpServletResponse response);
}
