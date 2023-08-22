package com.tourranger.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
	OUT_OF_STOCK(HttpStatus.BAD_REQUEST.value(), "소진된 상품입니다."),
	USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 사용자입니다. 회원가입이 필요합니다."),
	ITEM_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 상품입니다."),
	IMAGE_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 이미지입니다."),
	TYPE_NOT_IMAGE(HttpStatus.BAD_REQUEST.value(), "파일형식이 이미지파일이 아닙니다.");
	private final int errorCode;
	private final String errorMessage;
}
