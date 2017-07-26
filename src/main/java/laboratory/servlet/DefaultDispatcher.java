package laboratory.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * @since 2017-06-19
 * @author fixalot
 */
public class DefaultDispatcher extends HttpServlet {
	private static final long serialVersionUID = 6174311087878978970L;

	private static final Logger logger = LoggerFactory.getLogger(DefaultDispatcher.class);
	private DefaultViewResolver viewResolver = new DefaultViewResolver();

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

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * setCharacterEncoding을 호출하기 전에 request에 접근하면(정확히 뭘 건드리는게 문제인지는 모르겠지만) 나중에 인코딩을 적용해도 소용이 없다. 따라서 setCharacterEncoding 호출은 filter
		 * 레벨에서 하는게 적절함.
		 * 
		 * req.setCharacterEncoding("UTF-8");
		 */
		printParameters(req);
		
		// view와 data 분기 
		final String url = req.getRequestURI();
		final String viewSufix = ".view";
		final String dataSufix = ".data";
		if (url.lastIndexOf(viewSufix) == (url.length() - dataSufix.length())) {
			viewResolver.createView(req, resp);
		} else if (url.lastIndexOf(dataSufix) == (url.length() - dataSufix.length())) {
			logger.debug("HTTP method: {}", req.getMethod());
			
			JSONResponseObject responseObject = new JSONResponseObject();
			responseObject.setSuccess(true);
			responseObject.setMessage("hello there.");
			
//			resp.setStatus(200); // 생략해도 됨
			PrintWriter out = resp.getWriter();
		    out.print(new Gson().toJson(responseObject));
		}
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
			logger.debug(key + ": " + Arrays.toString(values));
		}
	}
}
