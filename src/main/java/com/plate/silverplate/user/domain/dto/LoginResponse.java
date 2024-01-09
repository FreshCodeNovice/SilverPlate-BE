package com.plate.silverplate.user.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginResponse {
    private String accessToken;
    private boolean firstLogin;

    @Builder
    public LoginResponse(String accessToken, boolean firstLogin) {
        this.accessToken = accessToken;
        this.firstLogin = firstLogin;
    }
}
