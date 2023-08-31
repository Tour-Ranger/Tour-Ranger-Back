package com.tourranger.purchase.controller;

import com.tourranger.common.dto.ApiResponseDto;
import com.tourranger.purchase.dto.PurchaseRequestDto;
import com.tourranger.purchase.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tour-ranger")
@Tag(name = "Purchase API", description = "Purchase에 관련된 API 정보를 담고 있습니다.")
public class PurchaseController {
	private final PurchaseService purchaseService;

	@Operation(summary = "상품 주문", description = "PurchaseRequestDto로 유저의 정보를 받아와서 주문을 완료합니다.")
	@PostMapping("/purchases")
	public ResponseEntity<ApiResponseDto> purchaseItem(@RequestParam String itemName,
													   @Valid @RequestBody PurchaseRequestDto requestDto) {
		purchaseService.purchaseItem(itemName, requestDto);
		return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(), "주문 완료"));
	}
}
