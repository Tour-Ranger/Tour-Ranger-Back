package com.tourranger.purchase.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestDto {
	@NotBlank
	@Email(message = "이메일 형식이 아닙니다")
	private String email;
}
