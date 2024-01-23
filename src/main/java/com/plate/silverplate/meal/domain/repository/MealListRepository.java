package com.plate.silverplate.meal.domain.repository;

import com.plate.silverplate.meal.domain.entity.MealList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealListRepository extends JpaRepository<MealList,Long> {
}
