package com.example.securingweb.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/app/users").setViewName("users");
		registry.addViewController("/app/users/**").setViewName("users");
		registry.addViewController("/authentication/login").setViewName("login");
		registry.addViewController("/authentication/register").setViewName("signup_form");
		registry.addViewController("/app").setViewName("app_page");
	}
}
