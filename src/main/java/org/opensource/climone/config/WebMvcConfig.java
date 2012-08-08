package org.opensource.climone.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration

@ImportResource(value = "classpath:spring/webflow-config.xml")
public class WebMvcConfig {

}
