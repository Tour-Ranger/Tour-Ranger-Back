package com.tourranger.purchase.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PurchaseRequestDto {
	private String email;
}
