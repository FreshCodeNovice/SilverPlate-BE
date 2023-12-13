package com.plate.silverplate.userPhysical.domain.entity;

import com.plate.silverplate.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_physical")
public class UserPhysical extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 1)
    @NotNull
    @Column(name = "gender", nullable = false, length = 1)
    private String gender;

    @NotNull
    @Column(name = "height", nullable = false)
    private int height;

    @NotNull
    @Column(name = "weight", nullable = false)
    private int weight;

    @NotNull
    @Column(name = "age", nullable = false)
    private int age;

    @NotNull
    @Column(name = "activity_coefficient", nullable = false)
    private int activityCoefficient;

    @Builder
    public UserPhysical(Long id, String gender, int height, int weight, int age, int activityCoefficient) {
        this.id = id;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.activityCoefficient = activityCoefficient;
    }
}