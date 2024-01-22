package com.plate.silverplate.meal.dto.response;

import com.plate.silverplate.meal.domain.entity.Meal;
import io.swagger.v3.oas.annotations.media.Schema;

public record MealCreateResponse(
        @Schema(description = "한끼 id")
        Long mealId
) {
    public static MealCreateResponse from(Meal meal){
        return new MealCreateResponse(
                meal.getId()
        );
    }
}
