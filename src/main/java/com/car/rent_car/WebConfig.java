package com.car.rent_car;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Allows requests from your React app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Adjust as needed
                .allowedHeaders("*")
                .allowCredentials(true); // If your frontend needs to send cookies or use authentication
    }
}
