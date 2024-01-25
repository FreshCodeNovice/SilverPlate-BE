package com.plate.silverplate.meal.domain.repository;

import com.plate.silverplate.meal.domain.entity.Favorite;
import com.plate.silverplate.meal.domain.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite,Long>{

}
