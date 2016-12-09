package noritersand;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.annotation.HandlesTypes;

@HandlesTypes({ServletTest.class})
public class WebAppInitializer implements ServletContainerInitializer {
	public void onStartup(Set<Class<?>> arg0, ServletContext context) throws ServletException {
		Dynamic registration = context.addServlet("servlet", new ServletTest());
		registration.addMapping("*.do");
		registration.setLoadOnStartup(1);
	}	
}
 