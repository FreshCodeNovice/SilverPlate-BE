package com.plate.silverplate.meal.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

import java.util.List;

public record MealCreateRequest (

        @Schema(description = "한끼 id")
        @Size(min = 1,message = "음식은 최소 1개 이상이어야 합니다.")
        List<MealListCreateRequest> mealList
) {}