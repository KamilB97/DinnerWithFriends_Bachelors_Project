package com.kamil.dinnerapp.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

public class CorsFilter extends GenericFilterBean implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		// HttpServletResponse response = (HttpServletResponse) servletResponse;
	    
		HttpServletResponse response  = (HttpServletResponse) servletResponse;
		HttpServletRequest request= (HttpServletRequest) servletRequest;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
		//httpResponse.setHeader("Access-Control-Allow-Origin", "null");
		response.setHeader("Access-Control-Allow-Headers", "Origin,Content-Type,X-Authorization,Accept,Authorization");
		
		
		response.setHeader("Access-Control-Allow--Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "86400 ");
		//httpResponse.setHeader("X-Requested-With", "XMLHttpRequest");
		
		System.out.println("**************** CORS CONFIGURATION COMPLETED **********************");
		
		chain.doFilter(servletRequest, servletResponse);
		
		
		
	}
	
	
	
	

}
