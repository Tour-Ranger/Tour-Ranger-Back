package com.tourranger.item.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@Operation(summary = "상품리스트 조회", description = "상품의 정보를 조회합니다.")
	@GetMapping("/items")
	public ResponseEntity<List<ItemResponseDto>> getItemList(
		@RequestParam(name = "search", required = false) String search,
		@RequestParam(name = "condition", required = false) String condition,
		Pageable pageable) {
		//검색어 입력으로 조회하는 경우
		if (search != null && !search.isEmpty()) {
			return ResponseEntity.ok().body(itemService.getSearchedItemList(search, condition, pageable));
		}
		//일반 조회하는 경우-Id순
		else
			return ResponseEntity.ok().body(itemService.getItemList(pageable));
	}

}
