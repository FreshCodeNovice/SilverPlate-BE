package com.plate.silverplate.userPhysical.domain.dto.response;

import com.plate.silverplate.userPhysical.domain.entity.UserPhysical;

public record UserPhysicalCreateResponse(
        Long userPhysicalId
) {
    public static UserPhysicalCreateResponse of(UserPhysical userPhysical){
        return new UserPhysicalCreateResponse(userPhysical.getId());
    }
}
