package com.plate.silverplate.user.jwt.fillter;

import com.plate.silverplate.common.exception.ErrorCode;
import com.plate.silverplate.common.exception.ErrorException;
import com.plate.silverplate.user.domain.entity.User;
import com.plate.silverplate.user.jwt.utill.JwtUtil;
import com.plate.silverplate.user.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = resolveToken(request);

        // 토큰 검사 생략(모두 허용 URL의 경우 토큰 검사 통과)
        if (!StringUtils.hasText(accessToken)) {
            doFilter(request, response, filterChain);
            return;
        }

        // AccessToken 만료 여부 확인
        if (!jwtUtil.verifyToken(accessToken)) {
            throw new JwtException("만료된 Access Token입니다.");
        }

        if (jwtUtil.verifyToken(accessToken)) {
            // AccessToken의 payload에 있는 email로 user를 조회한다.
            User findUser = userRepository.findByEmail(jwtUtil.getUid(accessToken))
                    .orElseThrow(() -> new ErrorException(ErrorCode.NON_EXISTENT_EMAIL));

            // SecurityContext에 인증 객체를 등록한다.
            Authentication auth = getAuthentication(findUser);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

    // request Header에서 토큰 추출
    public String resolveToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    // Authentication 생성
    public Authentication getAuthentication(User user) {
        return new UsernamePasswordAuthenticationToken(user, "",
                List.of(new SimpleGrantedAuthority(user.getRole())));
    }
}
