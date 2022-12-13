package lab.servlet.core.view;

import javax.servlet.http.HttpServletRequest;

/**
 * 기본 View
 *
 * @author fixalot
 * @since 2017-08-02
 */
public class View {
    private String viewName;

    public View(String viewName) {
        this.viewName = viewName;
    }

    public View(HttpServletRequest request) {
        this.setViewName(request);
    }

    public String getViewName() {
        return this.viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public void setViewName(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String path = requestURI.substring(0, requestURI.lastIndexOf('.'));
        viewName = path;
    }
}
