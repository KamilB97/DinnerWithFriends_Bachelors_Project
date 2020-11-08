package com.kamil.dinnerapp;

import java.util.Currency;

import javax.persistence.EntityManager;
import javax.servlet.FilterRegistration;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.kamil.dinnerapp.config.CorsFilter;

@SpringBootApplication
@EnableScheduling
public class DinnerappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DinnerappApplication.class, args);
		 			
		}
	
	@Bean
	public FilterRegistrationBean corsFilterRegistration() {
		
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new CorsFilter());
		registrationBean.setName("CORS Filter");
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(1);
		
		return registrationBean;
			
	}

}
