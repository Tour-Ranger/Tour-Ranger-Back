package com.tourranger.purchase.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PurchaseRequestDto {
	@NotBlank
	@Email
	private String email;
}
