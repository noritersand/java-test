package lab.servlet.core.invoker;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class SimpleMethodInvoker implements MethodInvoker {

    @Override
    public Object invoke(Object instance, Method method, HttpServletRequest request, HttpServletResponse response)
            throws IllegalAccessException, InvocationTargetException {
        return method.invoke(instance, request, response);
    }
}
