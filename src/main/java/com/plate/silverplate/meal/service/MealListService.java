package com.plate.silverplate.meal.service;

import com.plate.silverplate.common.exception.ErrorCode;
import com.plate.silverplate.common.exception.ErrorException;
import com.plate.silverplate.meal.domain.entity.Favorite;
import com.plate.silverplate.meal.domain.entity.Meal;
import com.plate.silverplate.meal.domain.entity.MealList;
import com.plate.silverplate.meal.domain.repository.MealListRepository;
import com.plate.silverplate.meal.dto.request.FavoriteCreateRequest;
import com.plate.silverplate.meal.dto.request.FavoriteUpdateRequest;
import com.plate.silverplate.meal.dto.request.MealCreateRequest;
import com.plate.silverplate.meal.dto.request.MealListCreateRequest;
import com.plate.silverplate.nutritionFact.domain.entity.NutritionFact;
import com.plate.silverplate.nutritionFact.service.NutritionFactService;
import com.plate.silverplate.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MealListService {

    private final MealListRepository mealListRepository;

    private final NutritionFactService nutritionFactService;
    /*
     * 한끼에 들어가는 음식 종류와 무게 저장 로직
     * mealCreateRequest안에 있는 mealListCreateRequest 값 반복문
     * 해당하는 값 저장
     * */
    @Transactional
    public void createMealListFromMeal(MealCreateRequest mealCreateRequest, Meal meal){
        for (MealListCreateRequest mealListCreateRequest : mealCreateRequest.mealList()) {
            NutritionFact nutritionFact = nutritionFactService.findById(mealListCreateRequest.nutritionFactId());
            MealList mealList = MealList.builder()
                    .meal(meal)
                    .gram(mealListCreateRequest.gram())
                    .nutritionFact(nutritionFact)
                    .build();
            mealListRepository.save(mealList);
        }
    }

    /*
     * 한끼에 들어가는 음식 종류와 무게 저장 로직
     * favoriteCreateRequest안에 있는 mealListCreateRequest 값 반복문
     * 해당하는 값 저장
     * */
    @Transactional
    public void createMealListFromFavorite(FavoriteCreateRequest favoriteCreateRequest, Favorite favorite){
        for (MealListCreateRequest mealListCreateRequest : favoriteCreateRequest.mealList()) {
            NutritionFact nutritionFact = nutritionFactService.findById(mealListCreateRequest.nutritionFactId());
            MealList mealList = MealList.builder()
                    .favorite(favorite)
                    .gram(mealListCreateRequest.gram())
                    .nutritionFact(nutritionFact)
                    .build();
            mealListRepository.save(mealList);
        }
    }

    @Transactional
    public void updateMealListFromFavorite(User user,FavoriteUpdateRequest favoriteUpdateRequest, Long id ){
        List<MealList> mealLists = findMealLists(id,user);
        for (int i = 0; i < mealLists.size(); i++) {
            MealList mealList = mealLists.get(i);
            MealListCreateRequest request = favoriteUpdateRequest.mealList().get(i);
            Long nutritionFactId = request.nutritionFactId();
            double gram = request.gram();
            NutritionFact nutritionFact = nutritionFactService.findById(nutritionFactId);
            mealList.updateMealList(gram,nutritionFact);
        }
    }

    public List<MealList> findMealLists(Long id,User user){
        return mealListRepository.findByFavoriteId(id,user)
                .orElseThrow(()->new ErrorException(ErrorCode.NOT_FOUND_FAVORITE_ID));
    }




}
