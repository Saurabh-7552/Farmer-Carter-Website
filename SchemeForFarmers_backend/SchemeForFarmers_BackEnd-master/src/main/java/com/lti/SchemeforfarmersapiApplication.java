package com.lti;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lti", "com.lti.controller", "com.lti.entity", "com.lti.repository", "com.lti.service","com.lti.exception","com.lti.status","com.lti.dto"})
public class SchemeforfarmersapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchemeforfarmersapiApplication.class, args);
	}
	
	
	@Bean
	public CorsFilter corsFilter() { CorsConfiguration config = new
	CorsConfiguration(); config.setAllowCredentials(true);
	config.setAllowedOrigins(Arrays.asList("http://localhost:4200/"));
	config.setAllowedHeaders(Arrays.asList("Origin",
	"Access-Control-Allow-Origin","Content-Type", "Accept", "Authorization",
	"Origin,Accept", "X-Requested-With", "Access-Control-Request-Method",
	"Access-Control-Request-Headers"));
	config.setExposedHeaders(Arrays.asList("Origin", "Accept", "Authorization",
	"content-type", "Content-Disposition", "Access-Control-Allow-Origin",
	"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
	config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS",
	"DELETE", "PATCH")); UrlBasedCorsConfigurationSource
	urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
	urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", config);
	return new CorsFilter(urlBasedCorsConfigurationSource); }
	}



