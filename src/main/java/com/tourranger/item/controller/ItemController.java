package com.tourranger.item.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourranger.item.dto.ItemResponseDto;
import com.tourranger.item.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tour-ranger")
@Tag(name = "Item API", description = "Item에 관련된 API 정보를 담고 있습니다.")
public class ItemController {
	private final ItemService itemService;

	@Operation(summary = "상품 조회", description = "ItemId에 맞는 상품의 정보를 조회합니다.")
	@GetMapping("/items/{itemId}")
	public ResponseEntity<ItemResponseDto> getItem(@PathVariable Long itemId) {
		ItemResponseDto responseDto = itemService.getItem(itemId);
		return ResponseEntity.ok().body(responseDto);
	}

}
