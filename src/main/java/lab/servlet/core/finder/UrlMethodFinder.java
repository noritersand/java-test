package lab.servlet.core.finder;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

@Slf4j
public class UrlMethodFinder implements MethodFinder {
    //	@SuppressWarnings("unused")

    @Override
    public Object[] findMethod(List<Object> instanceList, HttpServletRequest request, HttpServletResponse response) {
        String url = request.getRequestURI();

        // XXX 이걸 왜 주석 처리 했더라?
//		final String path = url.substring(0, url.lastIndexOf("."));
//		String methodName = path.substring(path.lastIndexOf("/") + 1, path.length());
//		if (methodName.contains("-")) { // 하이픈이 있으면 카멜케이스로 변환
//			methodName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, methodName);
//		}

        // find method and invoking
        Object[] returnObject = new Object[2];
        for (Object instance : instanceList) {
            Method[] methods = instance.getClass().getDeclaredMethods();
            for (Method method : methods) {
                UrlMapping mapping = method.getAnnotation(UrlMapping.class);
                if (url.equals(mapping.value())) {
                    returnObject[0] = instance;
                    returnObject[1] = method;
                    log.debug("method found: {}#{}", instance.getClass().getSimpleName(), method.getName());
                    return returnObject;
                }
            }
        }
        throw new NotFoundMappingException("");
    }
}
