package com.nashtech.ecommerce.security.jwt;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.nashtech.ecommerce.dto.response.ResponseAccessDenied;

@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		String accessDeniedResponse = new Gson()
				.toJson(new ResponseAccessDenied(HttpServletResponse.SC_UNAUTHORIZED,
						"Access denied", authException.getMessage(), new Date()));
		response.getWriter().print(accessDeniedResponse);

	}

}
