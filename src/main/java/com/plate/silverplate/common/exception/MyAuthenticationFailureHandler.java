package com.plate.silverplate.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode errorJson = mapper.createObjectNode();

        errorJson.put("status", HttpStatus.UNAUTHORIZED.value());
        errorJson.put("timestamp", LocalDateTime.now().toString());
        errorJson.put("message", exception.getMessage());

        response.getWriter().write(mapper.writeValueAsString(errorJson));
    }
}