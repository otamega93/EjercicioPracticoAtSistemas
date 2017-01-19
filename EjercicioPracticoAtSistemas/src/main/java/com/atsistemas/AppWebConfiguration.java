package com.atsistemas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@ComponentScan("com.atsistemas")
@EnableWebMvc
@EnableSpringDataWebSupport
public class AppWebConfiguration {

	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		
		return new RequestMappingHandlerMapping();
	}
	
}
