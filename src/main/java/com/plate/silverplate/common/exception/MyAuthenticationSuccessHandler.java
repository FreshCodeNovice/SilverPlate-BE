package com.plate.silverplate.common.exception;

import com.plate.silverplate.user.domain.dto.GeneratedToken;
import com.plate.silverplate.user.jwt.utill.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
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

        if (isExist) {
            String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/loginSuccess")
                    .queryParam("access_token", token.getAccessToken())
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();

            response.addHeader("Authorization", token.getRefreshToken());

            log.info("redirect : {}", targetUrl);

            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        } else {
            String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/signUp")
                    .queryParam("access_token", token.getAccessToken())
                    .queryParam("refresh_token", token.getRefreshToken())
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();

            response.addHeader("Authorization", token.getRefreshToken());

            log.info("redirect : {}", targetUrl);

            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        }
    }
}
