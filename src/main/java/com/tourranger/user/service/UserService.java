package com.tourranger.user.service;

import com.tourranger.user.dto.SignupRequestDto;

public interface UserService {

	/**
	 * 회원가입
	 *
	 * @param requestDto 회원가입을 위한 요청 정보
	 */
	void signup(SignupRequestDto requestDto);
}
