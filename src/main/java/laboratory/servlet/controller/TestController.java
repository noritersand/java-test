package laboratory.servlet.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import laboratory.servlet.bean.JSONResponseObject;
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
	
	public View senderEucKr(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		logger.debug(RequestUtil.getRequestParameter(httpRequest).toString());
		return new View("/test/submit/sender-euc-kr");
	}
	
	public View senderIso88591(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		logger.debug(RequestUtil.getRequestParameter(httpRequest).toString());
		return new View(httpRequest);
	}
	
	/**
	 * 유니코드 문자 전송 결과 화면
	 * 
	 * @param httpRequest
	 * @param httpResponse
	 * @return
	 * @author fixalot
	 */
	public View receiver(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		logger.debug(RequestUtil.getRequestParameter(httpRequest).toString());
		return new View(httpRequest);
	}
	
	/**
	 * 개발자는 hello hello하고 웁니다.
	 * 
	 * @param httpRequest
	 * @param httpResponse
	 * @return
	 * @author fixalot
	 */
	public JSONResponseObject readRequestHeader(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		final RequestParameter params = RequestUtil.getRequestParameter(httpRequest);
		logger.debug(params.toString());
		JSONResponseObject json = new JSONResponseObject();
		json.setSuccess(true);
		json.setMessage(params.toString());
		return json;
	}
	
	/**
	 * 헤더와 바디 둘 중 하나에 파라미터가 있겠쥬?
	 * 
	 * @param httpRequest
	 * @param httpResponse
	 * @return
	 * @author fixalot
	 */
	public Map<String, Object> readPayloadBody(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		final RequestParameter params = RequestUtil.getRequestParameter(httpRequest);
		final String body = RequestUtil.readBody(httpRequest);
		logger.debug("header: " + params.toString());
		logger.debug("body: " + body);
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("header", params.toString());
		response.put("body", body.toString());
		return response;
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
