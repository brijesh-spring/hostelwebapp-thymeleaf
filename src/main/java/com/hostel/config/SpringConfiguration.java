package com.hostel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class SpringConfiguration {

	/*public InternalResourceViewResolver internalResourceViewResolver()
	{
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	}*/
	
	//Autowired
	//Bean
	public ViewResolver viewResolver()
	{
		return new InternalResourceViewResolver();
	}
}
