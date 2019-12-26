package laboratory.servlet.core.invoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleMethodInvoker implements MethodInvoker {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SimpleMethodInvoker.class);

	@Override
	public Object invoke(Object instance, Method method, HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, InvocationTargetException {
		return method.invoke(instance, request, response);
	}
}
