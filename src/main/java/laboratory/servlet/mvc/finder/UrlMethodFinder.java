package laboratory.servlet.mvc.finder;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import laboratory.servlet.controller.TestController;

public class UrlMethodFinder implements MethodFinder {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MethodFinder.class);

	@Override
	public Method findMethod(HttpServletRequest request, HttpServletResponse response) {
		final String url = request.getRequestURI();
//		final String path = url.substring(0, url.lastIndexOf("."));
//		String methodName = path.substring(path.lastIndexOf("/") + 1, path.length());
//		if (methodName.contains("-")) { // 하이픈이 있으면 카멜케이스로 변환
//			methodName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, methodName);
//		}
		
		logger.trace("i'll find you. and i'll kill you!");
		
		// find method and invoking
		List<Class<?>> classList = new LinkedList<>();
		classList.add(TestController.class);
		for (Class<?> clazz : classList) {
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				UrlMapping mapping = method.getAnnotation(UrlMapping.class);
				if (url.equals(mapping.value())) {
					return method;
				}
			}
		}
		throw new CannotFindMappingException();
	}
}
