package com.plate.silverplate.meal.domain.repository;

import com.plate.silverplate.meal.domain.entity.MealList;
import com.plate.silverplate.user.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface MealListRepositoryCustom {
    Optional<List<MealList>> findByFavoriteId(Long id, User user);
}

