package com.plate.silverplate.userPhysical.domain.entity;

import com.plate.silverplate.common.entity.BaseTimeEntity;
import com.plate.silverplate.user.domain.entity.User;
import com.plate.silverplate.userPhysical.domain.dto.request.UserPhysicalRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_physical")
public class UserPhysical extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "height", nullable = false)
    private float height;

    @Column(name = "weight", nullable = false)
    private float weight;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "activity_coefficient", nullable = false)
    private int activityCoefficient;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public UserPhysical(Long id, Gender gender, float height, float weight, int age, int activityCoefficient, User user) {
        this.id = id;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.activityCoefficient = activityCoefficient;
        this.user = user;
    }

    public void updatePhysicalInfo(UserPhysicalRequest physicalRequest) {
        this.gender = physicalRequest.gender();
        this.height = physicalRequest.height();
        this.weight = physicalRequest.weight();
        this.age = physicalRequest.age();
        this.activityCoefficient = physicalRequest.activityCoefficient();
    }
}