package com.tourranger.purchase.service;

import com.tourranger.purchase.dto.PurchaseRequestDto;

public interface PurchaseService {
	/**
	 * 상품 주문
	 * @param itemId 주문할 상품 ID
	 * @param requestDto 상품 주문을 위한 요청 정보
	 */
	boolean purchaseItem(Long itemId, PurchaseRequestDto requestDto);
}
