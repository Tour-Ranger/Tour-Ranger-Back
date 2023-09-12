package com.tourranger.item.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tourranger.item.entity.Item;

public interface ItemRepositoryCustom {

	//메인페이지 상품 조회
	Page<Item> findAllByOrderById(Pageable pageable);
}
