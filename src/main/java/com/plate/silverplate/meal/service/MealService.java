package com.plate.silverplate.meal.service;

import com.plate.silverplate.meal.domain.entity.Meal;
import com.plate.silverplate.meal.domain.repository.MealRepository;
import com.plate.silverplate.meal.dto.request.MealCreateRequest;
import com.plate.silverplate.meal.dto.response.MealCreateResponse;
import com.plate.silverplate.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MealService {

    private final MealRepository mealRepository;
    private final MealListService mealListService;
    /*
    * 한끼 등록 로직
    * 현재 날짜에 데이터만 넣을 수 있도록 설정
    * 반환값으로 mealId를 반환
    * */
    @Transactional
    public MealCreateResponse createMeal(User user, MealCreateRequest mealCreateRequest){
        LocalDate now = LocalDate.now();
        Meal meal = Meal.builder()
                .user(user)
                .mealDate(now)
                .build();
        mealRepository.save(meal);
        mealListService.createMealListFromMeal(mealCreateRequest,meal);
        return MealCreateResponse.from(meal);
    }




}
