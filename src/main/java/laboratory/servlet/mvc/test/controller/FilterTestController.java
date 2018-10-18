package laboratory.servlet.mvc.test.controller;

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
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	/**
	 * HTMLTagFilter 테스트
	 */
	@UrlMapping("/filter-test/replace-httpservletrequest.view")
	public View drawReplaceHttpservletrequest(HttpServletRequest request, HttpServletResponse response) {
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
	@UrlMapping("/filter-test/replace-httpservletrequest.data")
	public JsonResponseObject replaceHttpservletrequest(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("instanceof HTMLTagFilterRequestWrapper: {}", String.valueOf(request instanceof HTMLTagFilterRequestWrapper));
		logger.debug("instanceof HttpServletRequestWrapper: {}", String.valueOf(request instanceof HttpServletRequestWrapper));
		logger.debug("instanceof ServletRequestWrapper: {}", String.valueOf(request instanceof ServletRequestWrapper));
		logger.debug("instanceof ServletRequest: {}", String.valueOf(request instanceof ServletRequest));
		
		final RequestParameter params = RequestUtil.getRequestParameter(request);
		logger.debug(params.toString());
		JsonResponseObject responseJSON = new JsonResponseObject();
		responseJSON.setSuccess(true);
		responseJSON.setMessage(params.toString());
		return responseJSON;
	}
}
