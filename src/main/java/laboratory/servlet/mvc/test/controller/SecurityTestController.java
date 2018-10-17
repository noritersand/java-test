package laboratory.servlet.mvc.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import laboratory.servlet.core.finder.UrlMapping;
import laboratory.servlet.core.view.View;

public class SecurityTestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	/**
	 * XSS(크로스 사이트 스크립트) 테스트
	 */
	@UrlMapping("/security-test/xss-test.view")
	public View drawXssTest(HttpServletRequest request, HttpServletResponse response) {
		return new View(request);
	}
	
	@UrlMapping("/security-test/xss-test.data")
	public void xssTest(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("first: {}", request.getParameter("first"));
		logger.debug("second: {}", request.getParameter("second"));
	}
}
