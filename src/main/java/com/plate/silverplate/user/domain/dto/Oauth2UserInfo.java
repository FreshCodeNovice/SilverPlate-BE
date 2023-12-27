package com.plate.silverplate.user.domain.dto;

public interface Oauth2UserInfo {
    String getProvider();
    String getNickName();
    String getEmail();
    String getProviderId();
    String getImageUrl();
}
