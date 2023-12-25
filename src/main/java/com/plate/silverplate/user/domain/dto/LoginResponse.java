package com.plate.silverplate.user.domain.dto;

import com.plate.silverplate.user.domain.entity.Role;
import lombok.*;

@Getter
@NoArgsConstructor
public class LoginResponse {
    private Long id;
    private String nickName;
    private String email;
    private String imageUrl;
    private Role role;
    private String tokenType;
    private String accessToken;
    private String refreshToken;

    @Builder
    public LoginResponse(Long id, String nickName, String email, String imageUrl, Role role, String tokenType, String accessToken, String refreshToken) {
        this.id = id;
        this.nickName = nickName;
        this.email = email;
        this.imageUrl = imageUrl;
        this.role = role;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}