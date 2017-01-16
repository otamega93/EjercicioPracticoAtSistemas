package com.atsistemas;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppWebContextInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
		springContext.scan("com.cursospring");
		
		AnnotationConfigWebApplicationContext springContextRest = new AnnotationConfigWebApplicationContext();
		springContextRest.scan("com.atsistemas");
		
		ServletRegistration servletRegistration = servletContext.addServlet("spring", new DispatcherServlet(springContext));
		servletRegistration.addMapping("/");
		
		ServletRegistration servletRegistrationRest = servletContext.addServlet("springRest", new DispatcherServlet(springContextRest));
		servletRegistrationRest.addMapping("/rest/*");
		
	}
	
}
