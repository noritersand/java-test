package laboratory.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WrongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String userName;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fillUserName(request);
		response.getWriter().write(userName);
	}

	private void fillUserName(HttpServletRequest request) {
		String temp = (String) request.getSession().getAttribute("USER_NAME");
		userName = temp != null ? temp : userName;
	}
}