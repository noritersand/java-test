package laboratory.servlet.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import laboratory.servlet.bean.JsonResponseObject;
import laboratory.servlet.view.View;
import laboratory.util.request.RequestParameter;
import laboratory.util.request.RequestUtil;

public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	/**
	 * 유니코드 문자 전송 테스트 화면.
	 * UTF-8 인코딩 사용.
	 * 
	 * @param request
	 * @param reponse
	 * @return
	 * @author fixalot
	 */
	public View sender(HttpServletRequest request, HttpServletResponse reponse) {
		return new View(request);
	}
	
	public View senderEucKr(HttpServletRequest request, HttpServletResponse response) {
		logger.debug(RequestUtil.getRequestParameter(request).toString());
		return new View("/test/submit/sender-euc-kr");
	}
	
	public View senderIso88591(HttpServletRequest request, HttpServletResponse response) {
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
	public View receiver(HttpServletRequest request, HttpServletResponse response) {
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
	public JsonResponseObject readRequestHeader(HttpServletRequest request, HttpServletResponse response) {
		final RequestParameter params = RequestUtil.getRequestParameter(request);
		logger.debug(params.toString());
		JsonResponseObject responseJSON = new JsonResponseObject();
		responseJSON.setSuccess(true);
		responseJSON.setMessage(params.toString());
		return responseJSON;
	}
	
	/**
	 * 헤더와 바디 둘 중 하나에 파라미터가 있겠쥬?
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author fixalot
	 */
	public Map<String, Object> readPayloadBody(HttpServletRequest request, HttpServletResponse response) {
		final RequestParameter params = RequestUtil.getRequestParameter(request);
		final String body = RequestUtil.readBody(request);
		logger.debug("header: " + params.toString());
		logger.debug("body: " + body);
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
	 * @param reponse
	 * @return
	 * @author fixalot
	 */
	public View parent(HttpServletRequest request, HttpServletResponse reponse) {
		return new View(request);
	}
	
	/**
	 * parseNumberFromString
	 * 
	 * @param request
	 * @param reponse
	 * @return
	 * @author fixalot
	 */
	public View parseNumberFromString(HttpServletRequest request, HttpServletResponse reponse) {
		return new View(request);
	}
}
