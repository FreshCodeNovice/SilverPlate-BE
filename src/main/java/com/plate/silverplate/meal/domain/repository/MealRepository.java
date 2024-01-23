package com.plate.silverplate.meal.domain.repository;

import com.plate.silverplate.meal.domain.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal,Long> {
}
