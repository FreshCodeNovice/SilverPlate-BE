package com.plate.silverplate.meal.controller;

import com.plate.silverplate.meal.dto.request.FavoriteCreateRequest;
import com.plate.silverplate.meal.dto.request.FavoriteUpdateRequest;
import com.plate.silverplate.meal.dto.response.FavoriteCreateResponse;
import com.plate.silverplate.meal.service.FavoriteService;
import com.plate.silverplate.user.domain.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favorites")
@Tag(name = "Favorite", description = "즐겨찾기 API 문서")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("")
    @Operation(summary = "Create Favorite", description = "즐겨찾기 등록 API")
    public ResponseEntity<FavoriteCreateResponse> create(@AuthenticationPrincipal User user,
                                                         @Valid @RequestBody FavoriteCreateRequest favoriteCreateRequest){
        FavoriteCreateResponse response = favoriteService.createFavorite(user, favoriteCreateRequest);
        return ResponseEntity.ok().body(response);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@AuthenticationPrincipal User user,
            @Valid @RequestBody FavoriteUpdateRequest favoriteUpdateRequest,
                                    @PathVariable Long id){
        favoriteService.updateFavorite(user,id,favoriteUpdateRequest);
        return ResponseEntity.ok().body("su");
    }

}
