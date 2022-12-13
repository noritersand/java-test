package lab.servlet.mvc.test.controller;

import lab.servlet.core.bean.JsonResponseObject;
import lab.servlet.core.finder.UrlMapping;
import lab.servlet.core.view.View;
import lab.util.request.RequestParameter;
import lab.util.request.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HTTP 테스트 컨트롤러
 *
 * @author fixalot
 * @since 2018-10-26
 */
public class HttpTestController {
    private static final String TEST_HTTP_YOU_SHOULD_BE_HERE_DATA = "/page/http/you-should-be-here.data";
    private static final String LOCATION = "Location";
    private static final Logger logger = LoggerFactory.getLogger(HttpTestController.class);

    /**
     * HTTP 응답코드 테스트.
     *
     * @param request
     * @param response
     * @return
     * @author fixal
     */
    @UrlMapping("/page/http/response-status-code-test.view")
    public View drawResponseStatusCodeTest(HttpServletRequest request, HttpServletResponse response) {

        return new View(request);
    }

    /**
     * 301 moved permanently 응답.
     * https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/301
     *
     * @param request
     * @param response
     * @throws IOException
     * @author fixal
     */
    @UrlMapping("/page/http/let-me-301-moved-permanently.data")
    public void letMe301MovedPermanently(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY); // 301
        response.setHeader(LOCATION, TEST_HTTP_YOU_SHOULD_BE_HERE_DATA);
        response.flushBuffer();
    }

    /**
     * 302 found 응답.
     * https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/302
     *
     * @param request
     * @param response
     * @throws IOException
     * @author fixal
     */
    @UrlMapping("/page/http/let-me-302-found.data")
    public void letMe302Found(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FOUND); // 302
        response.setHeader(LOCATION, TEST_HTTP_YOU_SHOULD_BE_HERE_DATA);
        response.flushBuffer();
//		response.sendRedirect("/page/http/you-should-be-here.data"); // 위 3줄과 같음
    }

    /**
     * 307 temporary redirect 응답.
     * https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/307
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @author fixal
     */
    @UrlMapping("/page/http/let-me-307-temporary-redirect.data")
    public void letMe307TemporaryRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT); // 307
        response.setHeader(LOCATION, TEST_HTTP_YOU_SHOULD_BE_HERE_DATA);
        response.flushBuffer();
    }

    /**
     * 308 permanent redirect 응답.
     * https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/308
     * 이 응답코드는 윈도우7과 8.1에서 작동하지 않는다 함.
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @author fixal
     */
    @UrlMapping("/page/http/let-me-308-permanent-redirect.data")
    public void letMe308PermanentRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(308);
        response.setHeader(LOCATION, TEST_HTTP_YOU_SHOULD_BE_HERE_DATA);
        response.flushBuffer();
    }

    /**
     * 리다이렉션 도착지
     *
     * @param request
     * @param response
     * @author fixal
     */
    @UrlMapping(TEST_HTTP_YOU_SHOULD_BE_HERE_DATA)
    public JsonResponseObject youShoudBeHere(HttpServletRequest request, HttpServletResponse response) {
        final RequestParameter params = RequestUtil.getRequestParameter(request);
        logger.debug("{}", params);
        JsonResponseObject responseJSON = new JsonResponseObject();
        responseJSON.setSuccess(true);
        responseJSON.setMessage(params.toString());
        return responseJSON;
    }

    /**
     * 받아라 교차출처 어택
     *
     * @param request
     * @param response
     * @return
     * @author fixalot
     */
    @UrlMapping("/page/http/take-my-cross-origin-attack.data")
    public JsonResponseObject takeMyCrossOriginAttack(HttpServletRequest request, HttpServletResponse response) {
//		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setHeader("Access-Control-Allow-Origin", "*");
        JsonResponseObject json = new JsonResponseObject();

        json.setSuccess(true);

        return json;
    }
}
