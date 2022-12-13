package lab.servlet.mvc.test.controller;

import lab.servlet.core.finder.UrlMapping;
import lab.servlet.core.view.View;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class SecurityTestController {

    /**
     * XSS(크로스 사이트 스크립트) 테스트
     */
    @UrlMapping("/page/security/xss-test.view")
    public View drawXssTest(HttpServletRequest request, HttpServletResponse response) {
        return new View(request);
    }

    @UrlMapping("/page/security/test-xss.data")
    public void testXss(HttpServletRequest request, HttpServletResponse response) {
        log.debug("first: {}", request.getParameter("first"));
        log.debug("second: {}", request.getParameter("second"));
    }
}
