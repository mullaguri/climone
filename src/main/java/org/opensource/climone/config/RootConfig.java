package org.opensource.climone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = { "org.opensource.climone.dao.impl", 
                                "org.opensource.climone.service.impl"})
@PropertySource("classpath:app-config.properties")
@Import({ PersistenceConfig.class, SecurityConfig.class, MailConfig.class, TaskConfig.class})
public class RootConfig {
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}