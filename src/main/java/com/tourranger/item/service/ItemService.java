package com.tourranger.item.service;

import com.tourranger.item.dto.ItemResponseDto;

public interface ItemService {
	/**
	 * 상품 조회
	 * @param itemName 조회할 상품 이름
	 * @return 조회한 상품 정보
	 */
	ItemResponseDto getItem(String itemName);
}
