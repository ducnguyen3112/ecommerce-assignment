package com.nashtech.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nashtech.ecommerce.security.jwt.AuthenticationEntryPointHandler;
import com.nashtech.ecommerce.security.jwt.AuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/swagger-ui/**");
		http.antMatcher("/v3/api-docs/**");
		http.cors().and().csrf().disable().exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler()).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").and()
				.authorizeRequests().antMatchers("/auth/**").permitAll().anyRequest()
				.authenticated();
		http.addFilterBefore(authenticationJwtTokenFilter(),
				UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public AuthenticationEntryPointHandler unauthorizedHandler() {
		return new AuthenticationEntryPointHandler();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationFilter authenticationJwtTokenFilter() {
		return new AuthenticationFilter();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	

}
