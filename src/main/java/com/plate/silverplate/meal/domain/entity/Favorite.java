package com.plate.silverplate.meal.domain.entity;

import com.plate.silverplate.common.entity.BaseTimeEntity;
import com.plate.silverplate.meal.dto.request.FavoriteCreateRequest;
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
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", length = 15)
    private String title;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @Builder
    public Favorite(Long id, String title,  Meal meal) {
        this.id = id;
        this.title = title;
        this.meal = meal;
    }
}