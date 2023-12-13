package com.plate.silverplate.registration.domain.entity;

import com.plate.silverplate.common.entity.BaseTimeEntity;
import com.plate.silverplate.foodRegistration.domain.entity.FoodRegistration;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "registration")
public class Registration extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(max = 255)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "status")
    private String status;

    @Size(max = 255)
    @Column(name = "response_content")
    private String responseContent;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "food_registration_id", nullable = false)
    private FoodRegistration foodRegistration;

    @Builder
    public Registration(Long id, String status, String responseContent, FoodRegistration foodRegistration) {
        this.id = id;
        this.status = status;
        this.responseContent = responseContent;
        this.foodRegistration = foodRegistration;
    }
}