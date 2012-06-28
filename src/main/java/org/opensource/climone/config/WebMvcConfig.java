package org.opensource.climone.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = { "org.opensource.climone.controller" })
@ImportResource(value = "classpath:spring/webflow-config.xml")
public class WebMvcConfig {

}
