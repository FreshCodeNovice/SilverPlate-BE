package com.plate.silverplate.userPhysical.domain.dto.request;

import com.plate.silverplate.userPhysical.domain.entity.Gender;
import jakarta.validation.constraints.*;

public record UserPhysicalRequest(
        @NotNull(message = "성별을 선택해주세요.")
        Gender gender,
        @DecimalMin(value = "50", message = "키는 50보다 크거나 같아야 합니다.")
        @DecimalMax(value = "300", message = "키는 300보다 작거나 같아야 합니다.")
        @NotNull(message = "키를 입력해주세요.")
        float height,
        @DecimalMin(value = "1", message = "체중은 1보다 크거나 같아야 합니다.")
        @DecimalMax(value = "300", message = "체중은 300보다 작거나 같아야 합니다.")
        @NotNull(message = "체중을 입력해주세요.")
        float weight,
        @Min(value = 1, message = "나이는 1보다 크거나 같아야 합니다.")
        @Max(value = 150, message = "나이는 150보다 작거나 같아야 합니다.")
        @NotNull(message = "나이를 입력해주세요.")
        int age,
        @Min(value = 1, message = "활동량은 1보다 크거나 같아야 합니다.")
        @Max(value = 5, message = "활동량은 5보다 작거나 같아야 합니다.")
        @NotNull(message = "활동량을 선택해주세요.")
        int activityCoefficient
) {
}
