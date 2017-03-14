package laboratory.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 6174311087878978970L;
	private static final Logger log = LoggerFactory.getLogger(ServletTest.class);

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
		req.setCharacterEncoding("UTF-8"); // 이거 안하면 post 한글 깨짐
		// 크롬 56, server.xml에 URIEncoding="UTF-8" 설정되어 있는 상태에서 테스트
		
		String uri = req.getRequestURI();
		log.debug("requested path: " + uri);

		printParameters(req);

		uri = uri.substring(0, uri.lastIndexOf("."));
		req.setAttribute("attr", "이야아아");
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/jsp/" + uri + ".jsp").forward(req, resp);
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

}
