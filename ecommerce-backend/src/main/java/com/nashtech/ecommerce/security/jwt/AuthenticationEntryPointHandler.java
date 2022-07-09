package com.nashtech.ecommerce.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashtech.ecommerce.dto.response.ResponseAccessDenied;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.error(authException.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper objectMapper = new ObjectMapper();
        String accessDeniedResponse = objectMapper.writeValueAsString(
                new ResponseAccessDenied(HttpServletResponse.SC_UNAUTHORIZED,
                        "Access denied", authException.getMessage(), new Date()));
        response.getWriter().print(accessDeniedResponse);
    }

}
