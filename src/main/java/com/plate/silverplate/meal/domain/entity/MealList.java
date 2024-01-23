package com.plate.silverplate.meal.domain.entity;

import com.plate.silverplate.common.entity.BaseTimeEntity;
import com.plate.silverplate.nutritionFact.domain.entity.NutritionFact;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "meal_list")
public class MealList extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`Key`", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nutrition_fact_id", nullable = false)
    private NutritionFact nutritionFact;

    @NotNull
    @Column(name = "gram", nullable = false)
    private double gram;
    @Builder
    public MealList(Long id, Meal meal, NutritionFact nutritionFact,double gram) {
        this.id = id;
        this.meal = meal;
        this.gram = gram;
        this.nutritionFact = nutritionFact;
    }
}