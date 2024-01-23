package com.plate.silverplate.user.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private boolean firstLogin;

    @Builder
    public LoginResponse(String accessToken, String refreshToken, boolean firstLogin) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.firstLogin = firstLogin;
    }
}
