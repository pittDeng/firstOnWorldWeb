package com.dsh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**").addResourceLocations("classpath:/pic/");
        registry.addResourceHandler("/videos/**").addResourceLocations("classpath:/videos/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/templates/js/");
    }
}
