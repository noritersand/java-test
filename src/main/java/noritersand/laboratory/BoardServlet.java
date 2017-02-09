package noritersand.laboratory;

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
 
    /*
     * service()는 요청방식(GET/POST)에 따라
     * doGet()이나 doPost() 메서드를 실행하도록 프로그램 되어 있다.
     * 전송방식을 알 수 없으면 get이라 간주한다.
     */
    
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
 
	private void forward(HttpServletRequest req, HttpServletResponse resp,    
            String path) throws ServletException, IOException {
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
            String[] values = (String[]) parameterMap.get(key);
            System.out.println(key + ": " + Arrays.toString(values));
        }
    }
 
    protected void process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	System.out.println(req.getRequestURL());
    	printParameters(req);
    	
//        resp.setCharacterEncoding("UTF-8");
//        JSONObject job = new JSONObject();
//        job.put("sayhi", "hi");
//    	resp.getWriter().print(job.toJSONString());
//    	forward(req, resp, "/WEB-INF/jsp/bbs/result.jsp");
    	forward(req, resp, "/WEB-INF/html/1.html");
    }
}