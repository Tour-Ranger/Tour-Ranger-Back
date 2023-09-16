package com.tourranger.user.controller;

import com.tourranger.common.dto.ApiResponseDto;
import com.tourranger.user.dto.SignupRequestDto;
import com.tourranger.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tour-ranger")
@RequiredArgsConstructor
@RestController
@Tag(name = "Auth API", description = "사용자의 회원 가입/로그인 API 정보를 담고 있습니다.")
public class UserController {
	private final UserService userService;

	@Operation(summary = "회원 가입", description = "SignupRequestDto를 통해 회원이 제출한 정보의 유효성 검사 후 통과 시 DB에 저장하고 성공 메시지를 반환합니다.")
	@PostMapping("/signup")
	public ResponseEntity<ApiResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto) {
		userService.signup(requestDto);
		return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(), "회원 가입 성공"));
	}

}
