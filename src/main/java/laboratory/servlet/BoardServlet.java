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

public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			System.out.println(key + ": " + Arrays.toString(values));
		}
	}

	protected void process(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		this.printParameters(req);

		req.setCharacterEncoding("utf-8");

		String contextPath = req.getContextPath();
		contextPath = contextPath.length() == 0 ? "/" : contextPath;

		String requestedUrl = req.getRequestURI();
		requestedUrl = (contextPath.length() > 1) 
				? requestedUrl.substring(contextPath.length(), requestedUrl.length()) 
				: requestedUrl;

		System.out.println("contextPath: " + contextPath);
		System.out.println("requestedUrl: " + requestedUrl);

		switch (requestedUrl) {
		case "/a.do":
			this.forward(req, resp, "/WEB-INF/jsp/sample.jsp");
			break;
		default:
			resp.sendRedirect(contextPath);
			break;
		}

        /*
        // 요청된 URI에 따른 응답 분기
        if (requestUri.indexOf("drawList.do") != -1) {
            // 게시글 리스트
            this.forward(req, resp, "/WEB-INF/view/list.jsp");
        } else if (requestUri.indexOf("drawPost.do") != -1) {
            // 글쓰기 폼
            this.forward(req, resp, "/WEB-INF/view/post.jsp");
        } else if (requestUri.indexOf("savePost.do") != -1) {
            // 글 저장
            BoardDAO dao = new BoardDAO();
            BoardDTO dto = new BoardDTO();
            dto.setSubject(req.getParameter("subject"));
            dto.setName(req.getParameter("name"));
            dto.setContent(req.getParameter("content"));
            dto.setPwd(req.getParameter("pwd"));
            dto.setIpAddr(req.getRemoteAddr());

            dao.insertBoard(dto); // DATA INSERT

            // 게시글 리스트로 리다이렉트
            resp.sendRedirect(contextPath + "/bbs/drawList.do");
        }
        */
	}
}