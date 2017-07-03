package laboratory.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultViewResolver {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(DefaultViewResolver.class);

	public void createView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		final String path = requestURI.substring(0, requestURI.lastIndexOf("."));

//		req.setAttribute("attr", "이야아아");
//		List<String> list = new ArrayList<>();
//		list.add("a");
//		list.add("b");
//		list.add("c");
//		req.setAttribute("list", list);

		forwardToJSP(req, resp, path);
	}

	private void forwardToJSP(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
		if (req.getContextPath().length() > 0) {
			path = path.replaceFirst(req.getContextPath(), "");
		}
		final String jspLocation = "/WEB-INF/jsp/" + path + ".jsp";
		req.getRequestDispatcher(jspLocation).forward(req, resp);
	}
}
