package laboratory.servlet.mvc.test.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import laboratory.servlet.core.bean.JsonResponseObject;
import laboratory.servlet.core.finder.UrlMapping;
import laboratory.servlet.core.view.View;
import laboratory.util.request.RequestParameter;
import laboratory.util.request.RequestUtil;

/**
 * HTTP 테스트 컨트롤러
 * 
 * @since 2018-10-26
 * @author fixalot
 */
public class HttpTestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	/**
	 * HTTP 응답코드 테스트
	 * 
	 * - https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/307
	 * - https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/308
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author fixal
	 */
	@UrlMapping("/http-test/response-status-code.view")
	public View drawResponseStatusCode(HttpServletRequest request, HttpServletResponse response) {
		
		return new View(request);
	}
	
	/**
	 * 307 temporary redirect 응답
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author fixal
	 * @throws IOException 
	 */
	@UrlMapping("/http-test/let-me-307-redirect.data")
	public void letMe307Redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT); // 307
		response.setHeader("Location", "/http-test/you-should-be-here.data");
		response.flushBuffer();
	}
	
	/**
	 * 302 found 응답
	 * 
	 * @param request
	 * @param response
	 * @author fixal
	 * @throws IOException 
	 */
	@UrlMapping("/http-test/let-me-302-found.data")
	public void letMe302Found(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_FOUND); // 302
		response.setHeader("Location", "/http-test/you-should-be-here.data");
		response.flushBuffer();
//		response.sendRedirect("/http-test/you-should-be-here.data"); // 위 3줄과 같음
	}
	
	/**
	 * 리다이렉션 도착지
	 * 
	 * @param request
	 * @param response
	 * @author fixal
	 */
	@UrlMapping("/http-test/you-should-be-here.data")
	public JsonResponseObject youShoudBeHere(HttpServletRequest request, HttpServletResponse response) {
		final RequestParameter params = RequestUtil.getRequestParameter(request);
		logger.debug(params.toString());
		JsonResponseObject responseJSON = new JsonResponseObject();
		responseJSON.setSuccess(true);
		responseJSON.setMessage(params.toString());
		return responseJSON;
	}
	
	@UrlMapping("/http-test/take-my-cross-origin-attack.data")
	public JsonResponseObject takeMyCrossOriginAttack(HttpServletRequest request, HttpServletResponse response) {
//		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		response.setHeader("Access-Control-Allow-Origin", "*");
		JsonResponseObject json = new JsonResponseObject();
		
		json.setSuccess(true);
		
		return json;
	}
}
