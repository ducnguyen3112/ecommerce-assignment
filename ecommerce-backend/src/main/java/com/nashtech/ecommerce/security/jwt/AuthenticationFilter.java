package com.nashtech.ecommerce.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nashtech.ecommerce.security.UserDetailsImpl;
import com.nashtech.ecommerce.service.UserService;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String token = getTokenFromRequest(request);
			if (token != null && jwtUtils.validateJwtToken(token)) {
				String email = jwtUtils.getEmailFromJwtToken(token);
				UserDetailsImpl userDetailsImpl = userService.loadUserByUsername(email);
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetailsImpl, null, userDetailsImpl.getAuthorities());
				authenticationToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			}
		} catch (Exception e) {
			log.error("Cannot set user authentication: {}", e);
		}
		
		filterChain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.substring("Bearer ".length());
		}
		return null;
	}
}
