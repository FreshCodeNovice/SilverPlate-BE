package com.plate.silverplate.meal.controller;

import com.plate.silverplate.meal.dto.request.MealCreateRequest;
import com.plate.silverplate.meal.dto.response.MealCreateResponse;
import com.plate.silverplate.meal.service.MealService;
import com.plate.silverplate.user.domain.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meals")
@Tag(name = "Meal", description = "한끼 API 문서")
public class MealController {

    private final MealService mealService;

    @PostMapping("")
    @Operation(summary = "Create Meal", description = "한끼 등록 API")
    public ResponseEntity<MealCreateResponse> create(@AuthenticationPrincipal User user,
                                    @Valid @RequestBody MealCreateRequest mealCreateRequest){
        MealCreateResponse response = mealService.createMeal(user, mealCreateRequest);
        return ResponseEntity.ok().body(response);
    }
}
