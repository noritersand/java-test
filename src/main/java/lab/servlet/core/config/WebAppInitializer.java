package lab.servlet.core.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import lab.servlet.core.DefaultDispatcher;
import lab.servlet.core.filter.CharacterEncodingFilter;
import lab.servlet.core.filter.HTMLTagFilter;
import lab.servlet.core.filter.LogbackMdcFilter;

/**
 * web.xml을 대체하는 서블릿 설정
 * 
 * @since 2017-06-19
 * @author fixalot
 */
@WebListener
public class WebAppInitializer implements ServletContextListener {
	private static final String DATA = "*.data";
	private static final String VIEW = "*.view";

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();

		// 케릭터인코딩 필터
		FilterRegistration.Dynamic encodingFilter = context.addFilter("encodingFilter", new CharacterEncodingFilter());
		encodingFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.FORWARD,
				DispatcherType.ERROR, DispatcherType.INCLUDE), false, VIEW, DATA);

		// 로그백 MDC 필터 등록. static resource 요청은 로그가 필요 없으므로 서블릿 URL 패턴과 동일하게 설정한다.
		FilterRegistration.Dynamic logbackMdcFilter = context.addFilter("logbackMdcFilter", new LogbackMdcFilter());
		logbackMdcFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.FORWARD), false,
				VIEW, DATA);
		
		FilterRegistration.Dynamic htmlTagFilter = context.addFilter("htmlTagFilter", new HTMLTagFilter());
		htmlTagFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.FORWARD), false,
				"/page/filter/replace-httpservletrequest-test.view", "/page/filter/replace-httpservletrequest.data");

		ServletRegistration.Dynamic servlet = context.addServlet("defaultDispatcher", new DefaultDispatcher());
		servlet.addMapping(VIEW);
		servlet.addMapping(DATA);
		servlet.setLoadOnStartup(1);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// do some when tomcat shutdown
	}
}
