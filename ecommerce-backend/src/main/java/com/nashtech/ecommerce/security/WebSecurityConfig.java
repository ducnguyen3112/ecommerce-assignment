package com.nashtech.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig implements WebMvcConfigurer {

	//private final UserDetailsService userDetailsService;

	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.authorizeRequests().antMatchers("/login/**").permitAll();
//		http.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").hasAnyAuthority("ROLE_USER");
//		http.authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").hasAnyAuthority("ROLE_ADMIN");
//		http.authorizeRequests().anyRequest().authenticated();
		http.authorizeRequests().anyRequest().permitAll();
//		http.addFilterBefore(new CustomauthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	

}
