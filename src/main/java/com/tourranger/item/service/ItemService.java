package com.tourranger.item.service;

import com.tourranger.item.dto.ItemResponseDto;

public interface ItemService {
	/**
	 * 상품 조회
	 * @param itemId 조회할 상품 ID
	 * @return 조회한 상품 정보
	 */
	ItemResponseDto getItem(Long itemId);
}
