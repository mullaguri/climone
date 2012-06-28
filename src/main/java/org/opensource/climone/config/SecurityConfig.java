package org.opensource.climone.config;

import org.springframework.context.annotation.*;

@Configuration
@ImportResource(value = "classpath:spring/security-config.xml")
public class SecurityConfig {}