package laboratory.servlet.core.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import laboratory.servlet.core.DefaultDispatcher;
import laboratory.servlet.core.filter.CharacterEncodingFilter;
import laboratory.servlet.core.filter.HTMLTagFilter;
import laboratory.servlet.core.filter.LogbackMdcFilter;

/**
 * web.xml을 대체하는 서블릿 설정
 * 
 * @since 2017-06-19
 * @author fixalot
 */
@WebListener
public class WebAppInitializer implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();

		// 케릭터인코딩 필터
		FilterRegistration.Dynamic encodingFilter = context.addFilter("encodingFilter", new CharacterEncodingFilter());
		encodingFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.FORWARD,
				DispatcherType.ERROR, DispatcherType.INCLUDE), false, "*.view", "*.data");

		// 로그백 MDC 필터 등록. static resource 요청은 로그가 필요 없으므로 서블릿 URL 패턴과 동일하게 설정한다.
		FilterRegistration.Dynamic logbackMdcFilter = context.addFilter("logbackMdcFilter", new LogbackMdcFilter());
		logbackMdcFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.FORWARD), false,
				"*.view", "*.data");
		
		FilterRegistration.Dynamic htmlTagFilter = context.addFilter("htmlTagFilter", new HTMLTagFilter());
		htmlTagFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.FORWARD), false,
				"/test/filter/replace-httpservletrequest-test.view", "/test/filter/replace-httpservletrequest.data");

		ServletRegistration.Dynamic servlet = context.addServlet("defaultDispatcher", new DefaultDispatcher());
		servlet.addMapping("*.view");
		servlet.addMapping("*.data");
		servlet.setLoadOnStartup(1);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// do some when tomcat shutdown
	}
}
