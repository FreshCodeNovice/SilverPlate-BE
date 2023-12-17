package com.plate.silverplate.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    JSON_NOT_PROCESSING(HttpStatus.BAD_REQUEST,"서버 내부적인 JSON 에러"),
    NON_EXISTENT_LIST_DTO(HttpStatus.BAD_REQUEST, "List DTO가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
