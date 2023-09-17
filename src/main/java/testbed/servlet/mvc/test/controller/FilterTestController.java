package testbed.servlet.mvc.test.controller;

import testbed.servlet.core.bean.JsonResponseObject;
import testbed.servlet.core.filter.HTMLTagFilterRequestWrapper;
import testbed.servlet.core.finder.UrlMapping;
import testbed.servlet.core.view.View;
import testbed.util.request.RequestParameter;
import testbed.util.request.RequestUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FilterTestController {

    /**
     * HTMLTagFilter 테스트
     */
    @UrlMapping("/page/filter/replace-httpservletrequest-test.view")
    public View drawReplaceHttpservletrequestTest(HttpServletRequest request, HttpServletResponse response) {
        log.debug("instanceof HTMLTagFilterRequestWrapper: {}", request instanceof HTMLTagFilterRequestWrapper);
        log.debug("instanceof HttpServletRequestWrapper: {}", request instanceof HttpServletRequestWrapper);
        log.debug("instanceof ServletRequestWrapper: {}", request instanceof ServletRequestWrapper);
        log.debug("instanceof ServletRequest: {}", request instanceof ServletRequest);

        log.debug("should be null: {}", null == request.getParameter("nothing"));
        return new View(request);
    }

    /**
     * HTMLTagFilter 테스트2
     */
    @UrlMapping("/page/filter/replace-httpservletrequest.data")
    public JsonResponseObject replaceHttpservletrequest(HttpServletRequest request, HttpServletResponse response) {
        log.debug("instanceof HTMLTagFilterRequestWrapper: {}", request instanceof HTMLTagFilterRequestWrapper);
        log.debug("instanceof HttpServletRequestWrapper: {}", request instanceof HttpServletRequestWrapper);
        log.debug("instanceof ServletRequestWrapper: {}", request instanceof ServletRequestWrapper);
        log.debug("instanceof ServletRequest: {}", request instanceof ServletRequest);

        Map<String, String> map = new HashMap<>();
        map.put("first", Arrays.toString(request.getParameterValues("first"))); // getParameterValues()와
        map.put("second", request.getParameter("second")); // getParamter()는 메서드가 교체되었음.
        log.debug(map.toString());

        RequestParameter params = RequestUtil.getRequestParameter(request); // getParameterMap()은 그대로
        log.debug(params.toString());

        JsonResponseObject responseJSON = new JsonResponseObject();
        responseJSON.setSuccess(true);
        responseJSON.setMessage(map.toString());
        return responseJSON;
    }
}
