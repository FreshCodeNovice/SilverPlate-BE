package com.plate.silverplate.user.domain.entity;

import com.plate.silverplate.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "user_profile")
public class UserProfile extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "provider", nullable = false, length = 40)
    private String provider;

    @Builder
    public UserProfile(Long id, String nickName, String imageUrl, String provider) {
        this.id = id;
        this.nickName = nickName;
        this.imageUrl = imageUrl;
        this.provider = provider;
    }

    public void updateProfileInfo(String nickName, String imageUrl) {
        this.nickName = nickName;
        this.imageUrl = imageUrl;
    }
}
