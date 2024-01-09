package com.plate.silverplate.user.jwt.utill;

import com.plate.silverplate.user.domain.dto.GeneratedToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${jwt.token.secret-key}")
    private String jwtSecretKey;
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public GeneratedToken generateToken(String email, String role) {
        // 토큰 생성
        String refreshToken = generateRefreshToken(email, role);
        String accessToken = generateAccessToken(email, role);

        // TODO: RT을 Redis에 저장
        return new GeneratedToken(accessToken, refreshToken);
    }

    public String generateRefreshToken(String email, String role) {
        // 토큰의 유효 기간을 milliSecond 단위로 설정 (2주)
        long refreshPeriod = 1000L * 60L * 60L * 24L * 14;

        // Claim에 이메일, 권한 세팅
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)   // 발행일자
                .setExpiration(new Date(now.getTime() + refreshPeriod))     // 만료 일시
                .signWith(SignatureAlgorithm.HS256, secretKey)      // HS256 알고리즘과 secretKey로 서명
                .compact();
    }

    public String generateAccessToken(String email, String role) {
        // 토큰의 유효 기간을 milliSecond 단위로 설정 (30분)
        long tokenPeriod = 1000L * 60L * 30L;
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);

        Date now = new Date();
        return
                Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(now)
                        .setExpiration(new Date(now.getTime() + tokenPeriod))
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact();

    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey) // 비밀키를 설정하여 파싱한다.
                    .parseClaimsJws(token);  // 주어진 토큰을 파싱하여 Claims 객체를 얻는다.

            return claims.getBody()
                    .getExpiration()
                    .after(new Date());  // 만료 시간이 현재 시간 이후인지 확인하여 유효성 검사 결과를 반환
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    // 토큰에서 email 추출
    public String getUid(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // 토큰에서 권한 추출
    public String getRole(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("role", String.class);
    }
}