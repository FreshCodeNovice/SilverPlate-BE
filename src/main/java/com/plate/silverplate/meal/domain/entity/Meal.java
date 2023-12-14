package com.plate.silverplate.meal.domain.entity;

import com.plate.silverplate.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "meal")
public class Meal extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "meal_date", nullable = false)
    private LocalDate mealDate;

    @Builder
    public Meal(Long id, LocalDate mealDate) {
        this.id = id;
        this.mealDate = mealDate;
    }
}