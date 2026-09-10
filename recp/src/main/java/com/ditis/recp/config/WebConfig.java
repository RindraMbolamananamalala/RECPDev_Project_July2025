package com.ditis.recp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // This maps the URL /images/** to the physical folder in the container
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/app/PatternsDiagramImages/");
    }
}