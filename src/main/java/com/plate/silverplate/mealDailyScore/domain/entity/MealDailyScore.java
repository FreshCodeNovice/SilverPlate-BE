package com.plate.silverplate.mealDailyScore.domain.entity;

import com.plate.silverplate.common.entity.BaseTimeEntity;
import com.plate.silverplate.user.domain.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "meal_daily_score")
public class MealDailyScore extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "score", nullable = false)
    private int score;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "meal_date", nullable = false)
    private LocalDate mealDate;

    @Builder
    public MealDailyScore(Long id, Integer score, User user, LocalDate mealDate) {
        this.id = id;
        this.score = score;
        this.user = user;
        this.mealDate = mealDate;
    }
}