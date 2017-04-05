package com.jimo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.jimo.config.JwtFilter;

@SpringBootApplication
public class AuthServerApplication {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean rbean = new FilterRegistrationBean();
		rbean.setFilter(new JwtFilter());
		rbean.addUrlPatterns("/user/*");// 过滤user下的链接
		return rbean;
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}
}
