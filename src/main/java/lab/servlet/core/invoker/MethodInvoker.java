package lab.servlet.core.invoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * method finder에서 찾은 메서드를 호출하는 인터페이스
 *
 * @author fixalot
 * @since 2017-08-09
 */
public interface MethodInvoker {
    public Object invoke(Object instance, Method method, HttpServletRequest request, HttpServletResponse response)
            throws IllegalAccessException, InvocationTargetException;
}
