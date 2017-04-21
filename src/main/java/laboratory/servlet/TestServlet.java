package laboratory.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(TestServlet.class);
	private static final String JSP_PREFIX = "/WEB-INF/jsp/";
	private static final String JSP_SUFIX = ".jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}

	private void forward(HttpServletRequest req, HttpServletResponse resp, String path)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(path);
		rd.forward(req, resp);
	}

	/**
	 * 요청 파라미터를 콘솔에 출력
	 */
	private void printParameters(HttpServletRequest req) {
		Map<String, String[]> parameterMap = req.getParameterMap();
		Set<String> keySet = parameterMap.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String[] values = parameterMap.get(key);
			log.debug(key + ": " + Arrays.toString(values));
		}
	}

	protected void process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.printParameters(req);

		req.setCharacterEncoding("UTF-8");

		String contextPath = req.getContextPath();
		contextPath = contextPath.length() == 0 ? "/" : contextPath;

		String requestURI = req.getRequestURI();
		requestURI = (contextPath.length() > 1)
				? requestURI.substring(contextPath.length(), requestURI.length())
				: requestURI;

		log.debug("contextPath: " + contextPath);
		log.debug("requestURI: " + requestURI);
		
		requestURI = requestURI.substring(0, requestURI.lastIndexOf("."));
		StringBuilder builder = new StringBuilder(JSP_PREFIX).append(requestURI).append(JSP_SUFIX);
		forward(req, resp, builder.toString());

		/*
		switch (requestURI) {
		case "/a.do":
			this.forward(req, resp, "/WEB-INF/jsp/sample.jsp");
			break;
		default:
			resp.sendRedirect(contextPath);
			break;
		}
		*/
	}
}