package com.plate.silverplate.user.domain.entity;

import com.plate.silverplate.common.entity.BaseTimeEntity;
import com.plate.silverplate.userPhysical.domain.entity.UserPhysical;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 30)
    @NotNull
    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @Size(max = 20)
    @NotNull
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Size(max = 30)
    @NotNull
    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Size(max = 20)
    @NotNull
    @Column(name = "role", nullable = false, length = 20)
    private String role;

    @Size(max = 40)
    @NotNull
    @Column(name = "provideId", nullable = false, length = 40)
    private String provideId;

    @Size(max = 40)
    @NotNull
    @Column(name = "provider", nullable = false, length = 40)
    private String provider;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_physcal_id", nullable = false)
    private UserPhysical userPhysical;

    @Builder
    public User(Long id, String password, String username, String email, String role, String provideId, String provider, UserPhysical userPhysical) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.email = email;
        this.role = role;
        this.provideId = provideId;
        this.provider = provider;
        this.userPhysical = userPhysical;
    }
}