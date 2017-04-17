package laboratory.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultViewResolver {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(DefaultViewResolver.class);
	
	public void createView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.substring(0, uri.lastIndexOf("."));
		req.setAttribute("attr", "이야아아");
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/jsp/" + uri + ".jsp").forward(req, resp);
	}
}
