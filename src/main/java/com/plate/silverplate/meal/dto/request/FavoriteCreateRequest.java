package com.plate.silverplate.meal.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record FavoriteCreateRequest (
        @Schema(description = "끼니 id")
        @NotNull(message = "끼니 id를 입력해주세요.")
        Long mealId,

        @Schema(description = "즐겨찾기 제목")
        @NotNull(message = "제목을 입력해주세요.")
        String title){
}
