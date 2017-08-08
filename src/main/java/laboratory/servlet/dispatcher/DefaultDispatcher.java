package laboratory.servlet.dispatcher;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CaseFormat;

import laboratory.servlet.controller.TestController;
import laboratory.servlet.view.DefaultViewResolver;

/**
 * 
 * 
 * @since 2017-06-19
 * @author fixalot
 */
public class DefaultDispatcher extends HttpServlet {
	private static final long serialVersionUID = 6174311087878978970L;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(DefaultDispatcher.class);
	
	@SuppressWarnings("unused")
	private static final String VIEW_SUFIX = ".view";
	@SuppressWarnings("unused")
	private static final String DATA_SUFIX = ".data";
	
	private DefaultViewResolver viewResolver = new DefaultViewResolver();
	private TestController testController = new TestController();

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

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * setCharacterEncoding을 호출하기 전에 request에 접근하면(정확히 뭘 건드리는게 문제인지는 모르겠지만) 나중에 인코딩을 적용해도 소용이 없다. 따라서 setCharacterEncoding 호출은 filter
		 * 레벨에서 하는게 적절함.
		 * 
		 * req.setCharacterEncoding("UTF-8");
		 */
		
		final String url = request.getRequestURI();
		final String path = url.substring(0, url.lastIndexOf("."));
		String methodName = path.substring(path.lastIndexOf("/") + 1, path.length());
		if (methodName.contains("-")) { // 하이픈이 있으면 카멜케이스로 변환
			methodName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, methodName);
		}
		
		// TODO 컨트롤러 인젝션 구현 필요: 지금은 단순히 메서드의 이름으로 매핑하며 컨트롤러도 명시되어 있으므로 스프링처럼 PATH를 의미하는 문자열을 어노테이션 등으로 설정할 수 있게 해야함
		// TODO 모든 클래스패스 내의 클래스 중 @Controller가 있는 클래스를 찾아서 @RequestMapping의 path값이 일치하는 메서드를 찾아야 함.
		Class<?> clazz = testController.getClass();
		// find method and invoking
		Method method = null;
		Object responseObject = null;
		try {
			method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
//			RequestMapping a = method.getAnnotation(RequestMapping.class); // TODO
			responseObject = method.invoke(testController, request, response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			// TODO sendError
			return;
		}
		
		viewResolver.sendResponse(responseObject, request, response);
	}
}
