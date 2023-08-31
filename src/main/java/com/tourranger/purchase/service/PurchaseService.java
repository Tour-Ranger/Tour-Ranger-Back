package com.tourranger.purchase.service;

import com.tourranger.purchase.dto.PurchaseRequestDto;

public interface PurchaseService {
	/**
	 * 상품 주문
	 * @param itemName 주문할 상품 이름
	 * @param requestDto 상품 주문을 위한 요청 정보
	 */
	void purchaseItem(String itemName, PurchaseRequestDto requestDto);
}
