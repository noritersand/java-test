package laboratory.servlet.mvc.test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import laboratory.servlet.core.bean.JsonResponseObject;
import laboratory.servlet.core.finder.UrlMapping;
import laboratory.servlet.core.view.View;
import laboratory.util.request.RequestParameter;
import laboratory.util.request.RequestUtil;

public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	/**
	 * 유니코드 문자 전송 테스트 화면.
	 * UTF-8 인코딩 사용.
	 */
	@UrlMapping("/test/submit/sender.view")
	public View drawSender(HttpServletRequest request, HttpServletResponse response) {
		return new View(request);
	}
	
	@UrlMapping("/test/submit/sender-euc-kr.view")
	public View drawSenderEucKr(HttpServletRequest request, HttpServletResponse response) {
		logger.debug(RequestUtil.getRequestParameter(request).toString());
		return new View("/test/submit/sender-euc-kr");
	}
	
	@UrlMapping("/test/submit/sender-iso-8859-1.view")
	public View drawSenderIso88591(HttpServletRequest request, HttpServletResponse response) {
		logger.debug(RequestUtil.getRequestParameter(request).toString());
		return new View(request);
	}
	
	/**
	 * 유니코드 문자 전송 결과 화면
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author fixalot
	 */
	@UrlMapping("/test/submit/receiver.view")
	public View drawReceiver(HttpServletRequest request, HttpServletResponse response) {
		logger.debug(RequestUtil.getRequestParameter(request).toString());
		return new View(request);
	}
	
	/**
	 * 개발자는 hello hello하고 웁니다.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author fixalot
	 */
	@UrlMapping("/test/readFormalParameter.data")
	public JsonResponseObject readFormalParameter(HttpServletRequest request, HttpServletResponse response) {
		final RequestParameter params = RequestUtil.getRequestParameter(request);
		logger.debug(params.toString());
		JsonResponseObject responseJSON = new JsonResponseObject();
		responseJSON.setSuccess(true);
		responseJSON.setMessage(params.toString());
		return responseJSON;
	}
	
	/**
	 * 둘 중 하나에 있겠쥬?
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author fixalot
	 */
	@UrlMapping("/test/readPayloadBody.data")
	public Map<String, Object> readPayloadBody(HttpServletRequest request, HttpServletResponse response) {
		final RequestParameter params = RequestUtil.getRequestParameter(request);
		final String body = RequestUtil.readBody(request);
		logger.debug("formal parameter: " + params.toString());
		logger.debug("payload body: " + body);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("success", true);
		responseMap.put("header", params.toString());
		responseMap.put("body", body.toString());
		return responseMap;
	}
	
	/**
	 * parent
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author fixalot
	 */
	@UrlMapping("/test/customtag/parent.view")
	public View parent(HttpServletRequest request, HttpServletResponse response) {
		return new View(request);
	}
	
	/**
	 * parseNumberFromString
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author fixalot
	 */
	@UrlMapping("/test/jstl/parseNumberFromString.view")
	public View drawParseNumberFromString(HttpServletRequest request, HttpServletResponse response) {
		return new View(request);
	}
	
	@UrlMapping("/test/include-test/with-jsp.view")
	public View drawWithJsp(HttpServletRequest request, HttpServletResponse response) {
		return new View(request);
	}
	
	@UrlMapping("/test/include-test/with-html.view")
	public View drawWithHtml(HttpServletRequest request, HttpServletResponse response) {
		return new View(request);
	}
	
	@UrlMapping("/test/scope/scope-test.view")
	public View drawScopeTest(HttpServletRequest request, HttpServletResponse response) {
		return new View(request);
	}
	
	@UrlMapping("/test/scope/setAttributes.data")
	public JsonResponseObject setAttributes(HttpServletRequest request, HttpServletResponse response) {
		JsonResponseObject json = new JsonResponseObject();
		
//		request.setAttribute("a", 1);
		request.setAttribute("b", 2);
		request.getSession().setAttribute("c", 3);
		request.getSession().getServletContext().setAttribute("d", 4);
		
		json.setSuccess(true);
		return json;
	}
	
	@UrlMapping("/test/what-is-payload-body.view")
	public View drawWhatIsPayloadBody(HttpServletRequest request, HttpServletResponse response) {
		return new View(request);
	}
	
	@UrlMapping("/test/session-invalidate-test.view")
	public View drawSessionInvalidateTest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession ss = request.getSession();
		
		logger.debug("session id: {}", ss.getId());
		
		ss.setAttribute("a", 123);
		int a = (int) ss.getAttribute("a");
		logger.debug(String.valueOf(a == 123));
		
		ss.invalidate(); // 세션 무효화
		
		// 아이디는 아직 그대로
		logger.debug("session id after invalidate: {}", ss.getId());
		
		// invalidate() 호출 후에는 set이나 get을 할 수 없음.
//		ss.setAttribute("b", 456); // IllegalStateException: setAttribute: Session has already been invalidated
//		ss.getAttribute("b"); // IllegalStateException: getAttribute: Session already invalidated

		return new View(request);
	}	
}
