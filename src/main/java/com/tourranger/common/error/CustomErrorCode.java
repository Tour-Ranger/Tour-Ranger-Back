package com.tourranger.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
//	예시
//	USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 사용자입니다."),
//	CARD_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 카드입니다.");
	OUT_OF_STOCK(HttpStatus.BAD_REQUEST.value(), "소진된 상품입니다.");

	private final int errorCode;
	private final String errorMessage;
}
