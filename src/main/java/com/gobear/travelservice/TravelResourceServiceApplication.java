package com.gobear.travelservice;

import com.gobear.travelservice.security.ResourceAccessFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class TravelResourceServiceApplication extends SpringBootServletInitializer {

    @Value("${services.auth}")
    private String authService;

    public static void main(String[] args) {
        SpringApplication.run(TravelResourceServiceApplication.class, args);
    }


    @Bean
    public FilterRegistrationBean resourceAccessFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ResourceAccessFilter());
        registrationBean.setInitParameters(Collections.singletonMap("services.auth", authService));
        registrationBean.addUrlPatterns("/protected-resource");

        return registrationBean;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TravelResourceServiceApplication.class);
    }
}
