package testbed.servlet.core.view;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class DefaultViewResolver {

    private static final String VIEW_LOCATION_PREFIX = "/WEB-INF/jsp/";
    private static final String VIEW_LOCATION_SUFIX = ".jsp";

    public void sendResponse(Object responseObject, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (responseObject instanceof View view) {
            this.forwardToJSP(view, request, response);
        } else {
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(new Gson().toJson(responseObject));
        }
    }

    private void forwardToJSP(View view, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = view.getViewName();
        if (0 < req.getContextPath().length()) {
            path = view.getViewName().replaceFirst(req.getContextPath(), "");
        }

        String jspLocation = DefaultViewResolver.VIEW_LOCATION_PREFIX + path + DefaultViewResolver.VIEW_LOCATION_SUFIX;
        req.getRequestDispatcher(jspLocation).forward(req, resp);
    }
}
