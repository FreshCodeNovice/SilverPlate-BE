package com.plate.silverplate.meal.service;

import com.plate.silverplate.common.exception.ErrorCode;
import com.plate.silverplate.common.exception.ErrorException;
import com.plate.silverplate.meal.domain.entity.Favorite;
import com.plate.silverplate.meal.domain.entity.Meal;
import com.plate.silverplate.meal.domain.repository.FavoriteRepository;
import com.plate.silverplate.meal.dto.request.FavoriteCreateRequest;
import com.plate.silverplate.meal.dto.response.FavoriteCreateResponse;
import com.plate.silverplate.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    private final MealService mealService;

    /*
    * 즐겨찾기 등록 API
    * MealId, title 요청값으로 받음
    * favoriteId 응답값으로 줌
    * */
    @Transactional
    public FavoriteCreateResponse createFavorite(User user, FavoriteCreateRequest favoriteCreateRequest) {
        Meal meal = mealService.findMeal(user,favoriteCreateRequest.mealId());
        Favorite favorite = Favorite.builder()
                .meal(meal)
                .title(favoriteCreateRequest.title())
                .build();
        favoriteRepository.save(favorite);
        return FavoriteCreateResponse.from(favorite);
    }


}
