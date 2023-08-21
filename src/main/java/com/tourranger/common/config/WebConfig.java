package com.tourranger.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		/* '/static/images/**'로 호출하는 자원은 '/static/images/' 폴더 아래에서 찾는다. */
		registry.addResourceHandler("/static/images/**")
			.addResourceLocations("classpath:/static/images/")
			.setCachePeriod(60 * 60 * 24 * 365);
	}
}
