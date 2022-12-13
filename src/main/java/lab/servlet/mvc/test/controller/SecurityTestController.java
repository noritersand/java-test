package lab.servlet.mvc.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lab.servlet.core.finder.UrlMapping;
import lab.servlet.core.view.View;

public class SecurityTestController {
    private static final Logger logger = LoggerFactory.getLogger(SecurityTestController.class);

    /**
     * XSS(크로스 사이트 스크립트) 테스트
     */
    @UrlMapping("/page/security/xss-test.view")
    public View drawXssTest(HttpServletRequest request, HttpServletResponse response) {
        return new View(request);
    }

    @UrlMapping("/page/security/test-xss.data")
    public void testXss(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("first: {}", request.getParameter("first"));
        logger.debug("second: {}", request.getParameter("second"));
    }
}
