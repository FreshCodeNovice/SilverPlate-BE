package com.plate.silverplate.meal.dto.response;

import com.plate.silverplate.meal.domain.entity.Favorite;
import io.swagger.v3.oas.annotations.media.Schema;

public record FavoriteCreateResponse(
        @Schema(description = "즐겨찾기 id")
        Long favoriteId
) {
    public static FavoriteCreateResponse from(Favorite favorite) {
        return new FavoriteCreateResponse(
                favorite.getId()
        );
    }
}
