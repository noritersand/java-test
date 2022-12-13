package lab.servlet.core;

import lab.servlet.core.finder.MethodFinder;
import lab.servlet.core.finder.NotFoundMappingException;
import lab.servlet.core.finder.UrlMethodFinder;
import lab.servlet.core.invoker.MethodInvoker;
import lab.servlet.core.invoker.SimpleMethodInvoker;
import lab.servlet.core.view.DefaultViewResolver;
import lab.servlet.mvc.fileupload.controller.OreillyFileUploadController;
import lab.servlet.mvc.test.controller.FilterTestController;
import lab.servlet.mvc.test.controller.HttpTestController;
import lab.servlet.mvc.test.controller.SecurityTestController;
import lab.servlet.mvc.test.controller.UncategorizedTestController;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @author fixalot
 * @since 2017-06-19
 */
@Slf4j
public class DefaultDispatcher extends HttpServlet {
    private static final long serialVersionUID = 6174311087878978970L;

    //	@SuppressWarnings("unused")

    @SuppressWarnings("unused")
    private static final String VIEW_SUFIX = ".view";
    @SuppressWarnings("unused")
    private static final String DATA_SUFIX = ".data";

    private final MethodFinder methodFinder = new UrlMethodFinder();
    private final MethodInvoker methodInvoker = new SimpleMethodInvoker();
    private final DefaultViewResolver viewResolver = new DefaultViewResolver();

    private final OreillyFileUploadController fileUploadWithOreilly = new OreillyFileUploadController();

    private final UncategorizedTestController testController = new UncategorizedTestController();
    private final SecurityTestController securityTestController = new SecurityTestController();
    private final FilterTestController filterTestController = new FilterTestController();
    private final HttpTestController httpTestController = new HttpTestController();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * setCharacterEncoding을 호출하기 전에 request에 접근하면(정확히 뭘 건드리는게 문제인지는 모르겠지만) 나중에 인코딩을 적용해도 소용이 없다. 따라서 setCharacterEncoding 호출은 filter
         * 레벨에서 하는게 적절함.
         *
         * req.setCharacterEncoding("UTF-8");
         */
        List<Object> instanceList = new LinkedList<>();
        instanceList.add(fileUploadWithOreilly);
        instanceList.add(testController);
        instanceList.add(securityTestController);
        instanceList.add(filterTestController);
        instanceList.add(httpTestController);

        Object[] object = null;
        try {
            object = methodFinder.findMethod(instanceList, request, response);
        } catch (NotFoundMappingException e) {
            log.error(e.getMessage(), e);
            response.sendError(404);
            return;
        }

        Object instance = object[0];
        Method method = (Method) object[1];

        try {
            Object responseObject = methodInvoker.invoke(instance, method, request, response);
            viewResolver.sendResponse(responseObject, request, response);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // send error
//			response.sendError(sc, msg);
            log.error(e.getMessage(), e);
            response.sendError(500);
        }
    }
}
