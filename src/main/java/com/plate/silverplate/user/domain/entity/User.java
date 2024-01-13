package com.plate.silverplate.user.domain.entity;

import com.plate.silverplate.common.entity.BaseTimeEntity;
import com.plate.silverplate.userPhysical.domain.entity.UserPhysical;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 30)
    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "role", nullable = false)
    private String role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_physcal_id")
    private UserPhysical userPhysical;

    @Builder
    public User(Long id, String email, String role, UserProfile userProfile, UserPhysical userPhysical) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.userProfile = userProfile;
        this.userPhysical = userPhysical;
    }

    public static User createUser(String email, String nickName, String provider, String imageUrl) {
        UserProfile profile = UserProfile.builder()
                .nickName(nickName)
                .provider(provider)
                .imageUrl(imageUrl)
                .build();

        User user = User.builder()
                .email(email)
                .role("ROLE_USER")
                .build();

        user.updateUserProfile(profile);

        return user;
    }

    public void updateUserProfile(UserProfile profile) {
        this.userProfile = profile;
    }
}