package com.plate.silverplate.meal.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record FavoriteUpdateRequest(
        @Schema(description = "즐겨찾기 제목")
        @NotNull(message = "제목을 입력해주세요.")
        String title,

        @Schema(description = "한끼 id")
        @Size(min = 1,message = "음식은 최소 1개 이상이어야 합니다.")
        List<MealListCreateRequest> mealList
) {
}
