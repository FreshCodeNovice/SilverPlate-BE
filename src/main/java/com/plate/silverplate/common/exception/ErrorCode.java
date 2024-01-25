package com.plate.silverplate.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    JSON_NOT_PROCESSING(HttpStatus.BAD_REQUEST,"서버 내부적인 JSON 에러"),
    NON_EXISTENT_LIST_DTO(HttpStatus.BAD_REQUEST, "List DTO가 존재하지 않습니다."),
    UNAUTHORIZED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "권한없는 Refresh Token입니다."),
    NON_EXISTENT_EMAIL(HttpStatus.NOT_FOUND, "해당 EMAIL의 사용자가 존재하지 않습니다."),
    NOT_FOUND_NUTRITION_FACT(HttpStatus.NOT_FOUND,"음식 영양정보를 찾을 수 없습니다."),
    NON_EXISTENT_USER(HttpStatus.NOT_FOUND, "해당 사용자의 신체 정보가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
