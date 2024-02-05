package com.plate.silverplate.meal.domain.repository;

import com.plate.silverplate.meal.domain.entity.MealList;
import com.plate.silverplate.user.domain.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.plate.silverplate.meal.domain.entity.QMealList.mealList;
import static com.plate.silverplate.meal.domain.entity.QFavorite.favorite;

@Repository
@RequiredArgsConstructor
public class MealListRepositoryCustomImpl implements MealListRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<List<MealList>> findByFavoriteId (Long id, User user){
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(mealList)
                        .leftJoin(favorite)
                        .on(mealList.favorite.id.eq(favorite.id))
                        .where(favorite.id.eq(id),
                                favorite.user.eq(user))
                        .orderBy(mealList.id.asc())
                        .fetch()
        );
    }

}
