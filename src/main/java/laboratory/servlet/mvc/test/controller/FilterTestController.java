package laboratory.servlet.mvc.test.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import laboratory.servlet.core.bean.JsonResponseObject;
import laboratory.servlet.core.filter.HTMLTagFilterRequestWrapper;
import laboratory.servlet.core.finder.UrlMapping;
import laboratory.servlet.core.view.View;
import laboratory.util.request.RequestParameter;
import laboratory.util.request.RequestUtil;

public class FilterTestController {
	private static final Logger logger = LoggerFactory.getLogger(FilterTestController.class);
	
	/**
	 * HTMLTagFilter 테스트
	 */
	@UrlMapping("/page/filter/replace-httpservletrequest-test.view")
	public View drawReplaceHttpservletrequestTest(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("instanceof HTMLTagFilterRequestWrapper: {}", String.valueOf(request instanceof HTMLTagFilterRequestWrapper));
		logger.debug("instanceof HttpServletRequestWrapper: {}", String.valueOf(request instanceof HttpServletRequestWrapper));
		logger.debug("instanceof ServletRequestWrapper: {}", String.valueOf(request instanceof ServletRequestWrapper));
		logger.debug("instanceof ServletRequest: {}", String.valueOf(request instanceof ServletRequest));
		
		logger.debug("should be null: {}", request.getParameter("nothing") == null);
		return new View(request);
	}
	
	/**
	 * HTMLTagFilter 테스트2
	 */
	@UrlMapping("/page/filter/replace-httpservletrequest.data")
	public JsonResponseObject replaceHttpservletrequest(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("instanceof HTMLTagFilterRequestWrapper: {}", String.valueOf(request instanceof HTMLTagFilterRequestWrapper));
		logger.debug("instanceof HttpServletRequestWrapper: {}", String.valueOf(request instanceof HttpServletRequestWrapper));
		logger.debug("instanceof ServletRequestWrapper: {}", String.valueOf(request instanceof ServletRequestWrapper));
		logger.debug("instanceof ServletRequest: {}", String.valueOf(request instanceof ServletRequest));
		
		Map<String, String> map = new HashMap<>();
		map.put("first", Arrays.toString(request.getParameterValues("first"))); // getParameterValues()와
		map.put("second", request.getParameter("second")); // getParamter()는 메서드가 교체되었음.
		logger.debug(map.toString());
		
		final RequestParameter params = RequestUtil.getRequestParameter(request); // getParameterMap()은 그대로
		logger.debug(params.toString());
		
		JsonResponseObject responseJSON = new JsonResponseObject();
		responseJSON.setSuccess(true);
		responseJSON.setMessage(map.toString());
		return responseJSON;
	}
}
