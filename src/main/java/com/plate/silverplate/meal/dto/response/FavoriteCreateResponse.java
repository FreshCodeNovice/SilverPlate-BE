package com.plate.silverplate.meal.dto.response;

import com.plate.silverplate.meal.domain.entity.Favorite;
import io.swagger.v3.oas.annotations.media.Schema;

public record FavoriteCreateResponse(
        @Schema(description = "즐겨찾기 id")
        Long favoriteId,
        @Schema(description = "즐겨찾기 id")
        String title

) {
    public static FavoriteCreateResponse from(Favorite favorite) {
        return new FavoriteCreateResponse(
                favorite.getId(),
                favorite.getTitle()
        );
    }
}
