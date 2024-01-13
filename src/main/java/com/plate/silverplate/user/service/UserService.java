package com.plate.silverplate.user.service;

import com.plate.silverplate.common.exception.ErrorCode;
import com.plate.silverplate.common.exception.ErrorException;
import com.plate.silverplate.user.domain.dto.TokenResponse;
import com.plate.silverplate.user.jwt.utill.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final RedisService redisService;
    private final JwtUtil jwtUtil;

    // refresh token으로 access token이 만료 되었을 경우 재발급
    public TokenResponse accessTokenByRefreshToken(String accessTokenHeader, String refreshToken) {
        String accessToken = jwtUtil.resolveToken(accessTokenHeader);

        String id = jwtUtil.getUid(accessToken);
        String data = redisService.getValues(id);

        if (data==null || !data.equals(refreshToken)) {
            log.info("Invalid Token");
            throw new ErrorException(ErrorCode.UNAUTHORIZED_REFRESH_TOKEN);
        }
        String role = jwtUtil.getRole(accessToken);

        return new TokenResponse(jwtUtil.generateAccessToken(id, role));
    }

    // 로그아웃시, redis에 refresh Token을 삭제하고, 로그아웃된 access Token 기록
    @Transactional
    public void logout(String accessTokenHeader) {
        String accessToken = jwtUtil.resolveToken(accessTokenHeader);

        jwtUtil.verifyToken(accessToken);

        String id = jwtUtil.getUid(accessToken);
        long time = jwtUtil.getExpiration(accessToken);     // access token 만료까지 남은 시간

        redisService.setValuesWithTimeout(accessToken, "logout", time);
        redisService.deleteValues(id);
    }
}
