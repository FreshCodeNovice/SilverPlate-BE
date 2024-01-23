package com.plate.silverplate.meal.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record MealListCreateRequest(
        @Schema(description = "음식 영양 정보 id",example = "1")
        @NotNull(message = "음식 영양 정보 id를 입력해주세요.")
        Long nutritionFactId,
        @Schema(description = "음식 무게 (gram)",example = "1")
        @NotNull(message = "음식의 무게를 입력해주세요.")
        double gram
) {
}
