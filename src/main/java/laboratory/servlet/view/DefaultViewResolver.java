package laboratory.servlet.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class DefaultViewResolver {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(DefaultViewResolver.class);

	private static final String VIEW_LOCATION_PREFIX = "/WEB-INF/jsp/";
	private static final String VIEW_LOCATION_SUFIX = ".jsp";

	public void sendResponse(Object responseObject, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (responseObject instanceof View) {
			View view = (View) responseObject;
			forwardToJSP(view, req, resp);
		} else {
			resp.setStatus(200);
			PrintWriter out = resp.getWriter();
		    out.print(new Gson().toJson(responseObject));
		}
	}
	
	private void forwardToJSP(View view, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = view.getViewName();
		if (req.getContextPath().length() > 0) {
			path = view.getViewName().replaceFirst(req.getContextPath(), "");
		}
		
		final String jspLocation = VIEW_LOCATION_PREFIX + path + VIEW_LOCATION_SUFIX;
		req.getRequestDispatcher(jspLocation).forward(req, resp);
	}
}
