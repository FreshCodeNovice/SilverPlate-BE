package com.plate.silverplate.meal.domain.entity;

import com.plate.silverplate.common.entity.BaseTimeEntity;
import com.plate.silverplate.meal.domain.entity.Meal;
import com.plate.silverplate.user.domain.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "favorite")
public class Favorite extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(max = 255)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "title", length = 50)
    private String title;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @Builder
    public Favorite(Long id, String title, User user, Meal meal) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.meal = meal;
    }
}