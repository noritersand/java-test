package laboratory.servlet.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import laboratory.servlet.DefaultDispatcher;
import laboratory.servlet.filter.FilterTest;

@WebListener
public class WebAppInitializer implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();

		FilterRegistration.Dynamic filter = context.addFilter("filterTest", new FilterTest());
		filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, "/*");

		ServletRegistration.Dynamic servlet = context.addServlet("defaultDispatcher", new DefaultDispatcher());
		servlet.addMapping("*.do");
		servlet.setLoadOnStartup(1);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// do some when tomcat shutdown
	}
}