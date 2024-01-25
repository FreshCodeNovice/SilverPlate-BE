package com.plate.silverplate.userPhysical.domain.dto.response;

import com.plate.silverplate.userPhysical.domain.entity.Gender;
import com.plate.silverplate.userPhysical.domain.entity.UserPhysical;
import lombok.Builder;

@Builder
public record UserPhysicalResponse(
        Long id,
        Gender gender,
        float height,
        float weight,
        int age,
        int activityCoefficient
) {
    public static UserPhysicalResponse of(UserPhysical userPhysical) {
        return UserPhysicalResponse.builder()
                .id(userPhysical.getId())
                .gender(userPhysical.getGender())
                .height(userPhysical.getHeight())
                .weight(userPhysical.getWeight())
                .age(userPhysical.getAge())
                .activityCoefficient(userPhysical.getActivityCoefficient())
                .build();
    }
}
