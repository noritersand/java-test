package laboratory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		// TODO Auto-generated method stub
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		log.debug("requested path: " + uri);
		
		uri = uri.substring(0, uri.lastIndexOf("."));
		req.setAttribute("attr", "이야아아");
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/jsp/" + uri + ".jsp").forward(req, resp);
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}
}
