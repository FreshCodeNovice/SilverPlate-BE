package com.plate.silverplate.user.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nickName", nullable = false)
    private String nickName;

    @OneToOne(mappedBy = "userProfile", fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    private User user;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Size(max = 40)
    @NotNull
    @Column(name = "provider_id", nullable = false, length = 40)
    private String providerId;

    @Size(max = 40)
    @NotNull
    @Column(name = "provider", nullable = false, length = 40)
    private String provider;

    @Builder
    public UserProfile(Long id, String nickName, User user, String imageUrl, String providerId, String provider) {
        this.id = id;
        this.nickName = nickName;
        this.user = user;
        this.imageUrl = imageUrl;
        this.providerId = providerId;
        this.provider = provider;
    }

    public static UserProfile createProfile(String nickName, String provider, String providerId, String imageUrl) {
        return UserProfile.builder()
                .nickName(nickName)
                .provider(provider)
                .providerId(providerId)
                .imageUrl(imageUrl)
                .build();
    }
}
