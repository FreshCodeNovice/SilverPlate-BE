package com.plate.silverplate.meal.service;

import com.plate.silverplate.common.exception.ErrorCode;
import com.plate.silverplate.common.exception.ErrorException;
import com.plate.silverplate.meal.domain.entity.Meal;
import com.plate.silverplate.meal.domain.entity.MealList;
import com.plate.silverplate.meal.domain.repository.MealListRepository;
import com.plate.silverplate.meal.domain.repository.MealRepository;
import com.plate.silverplate.meal.dto.request.MealCreateRequest;
import com.plate.silverplate.meal.dto.request.MealListCreateRequest;
import com.plate.silverplate.meal.dto.response.MealCreateResponse;
import com.plate.silverplate.nutritionFact.domain.entity.NutritionFact;
import com.plate.silverplate.nutritionFact.service.NutritionFactService;
import com.plate.silverplate.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MealService {

    private final MealRepository mealRepository;
    private final MealListRepository mealListRepository;

    private final NutritionFactService nutritionFactService;

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
        createMealList(mealCreateRequest,meal);
        return MealCreateResponse.from(meal);
    }

    /*
    * 한끼에 들어가는 음식 종류와 무게 저장 로직
    * mealCreateRequest안에 있는 mealListCreateRequest 값 반복문
    * 해당하는 값 저장
    * */
    @Transactional
    public void createMealList(MealCreateRequest mealCreateRequest,Meal meal){
        for (MealListCreateRequest mealListCreateRequest : mealCreateRequest.mealList()) {
            NutritionFact nutritionFact = nutritionFactService.findId(mealListCreateRequest.nutritionFactId());
            MealList mealList = MealList.builder()
                    .meal(meal)
                    .gram(mealListCreateRequest.gram())
                    .nutritionFact(nutritionFact)
                    .build();
            mealListRepository.save(mealList);
        }
    }

    /*
    * mealId를 통해 Meal을 구해주는 로직
    * meal에 user값이 현재 user가 아닐 시 예외 값 출력
    * */
    @Transactional
    public Meal findMeal(User user,Long mealId){
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new ErrorException(ErrorCode.NOT_FOUND_MEAL));
        if(!meal.getUser().equals(user)){
            throw new ErrorException(ErrorCode.NOT_FOUND_MEAL);
        }
        return meal;
    }
}
