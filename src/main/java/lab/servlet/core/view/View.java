package lab.servlet.core.view;

import javax.servlet.http.HttpServletRequest;

/**
 * 기본 View
 * 
 * @since 2017-08-02
 * @author fixalot
 */
public class View {
	private String viewName;

	public View(String viewName) {
		this.viewName = viewName;
	}

	public View(HttpServletRequest request) {
		setViewName(request);
	}
	
	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
	public void setViewName(HttpServletRequest request) {
		final String requestURI = request.getRequestURI();
		final String path = requestURI.substring(0, requestURI.lastIndexOf('.'));
		this.viewName = path;
	}
}
