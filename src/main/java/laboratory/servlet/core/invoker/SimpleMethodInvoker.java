package laboratory.servlet.core.invoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleMethodInvoker implements MethodInvoker {
	private static final Logger logger = LoggerFactory.getLogger(SimpleMethodInvoker.class);

	@Override
	public Object invoke(Object instance, Method method, HttpServletRequest request, HttpServletResponse response) {
		try {
			return method.invoke(instance, request, response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}
