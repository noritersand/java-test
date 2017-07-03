package laboratory.servlet;

import java.io.IOException;
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
		viewResolver.createView(req, resp);
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
