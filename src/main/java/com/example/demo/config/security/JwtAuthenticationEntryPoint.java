package com.example.demo.config.security;

import jakarta.servlet.ServletException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {


    @Override
    public void commence(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(401);
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().print("{\"timestamp\":\""+new Date()+"\",\"status\":401,\"error\":\"Unauthorized\",\"message\":\""+authException.getMessage()+"\"}");
        response.getWriter().flush();
    }
}