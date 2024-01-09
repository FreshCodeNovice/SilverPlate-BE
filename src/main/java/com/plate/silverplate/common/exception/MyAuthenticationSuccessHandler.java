package com.plate.silverplate.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plate.silverplate.user.domain.dto.GeneratedToken;
import com.plate.silverplate.user.domain.dto.LoginResponse;
import com.plate.silverplate.user.jwt.utill.JwtUtil;
import com.plate.silverplate.user.service.OauthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final OauthService oauthService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 인증된 사용자 정보
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        // 회원 존재 여부
        boolean isExist = oAuth2User.getAttribute("exist");
        String role = oAuth2User.getAuthorities().stream()
                .findFirst()
                .orElseThrow(IllegalAccessError::new)
                .getAuthority();

        // 토큰 생성
        GeneratedToken token = jwtUtil.generateToken(email, role);

        ObjectMapper objectMapper = new ObjectMapper();

        if (isExist) {
            LoginResponse tokenResponse = LoginResponse.builder()
                    .accessToken(token.getAccessToken())
                    .firstLogin(false)
                    .build();
            String jsonBody = objectMapper.writeValueAsString(tokenResponse);

            // resonse 설정
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(jsonBody);
            response.getWriter().flush();

        } else {
            oauthService.save(oAuth2User);      // 회원 가입

            LoginResponse tokenResponse = LoginResponse.builder()
                    .accessToken(token.getAccessToken())
                    .firstLogin(true)
                    .build();
            String jsonBody = objectMapper.writeValueAsString(tokenResponse);

            // resonse 설정
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(jsonBody);
            response.getWriter().flush();
        }
    }
}
