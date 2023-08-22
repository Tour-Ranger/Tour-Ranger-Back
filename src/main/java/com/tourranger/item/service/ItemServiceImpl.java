package com.tourranger.item.service;

import org.springframework.stereotype.Service;

import com.tourranger.common.error.CustomErrorCode;
import com.tourranger.common.exception.CustomException;
import com.tourranger.image.service.ImageService;
import com.tourranger.item.entity.Item;
import com.tourranger.item.repository.ItemRepository;
import com.tourranger.item.repository.ItemRepositoryCustom;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
	ItemRepository itemRepository;

	public Item findItem(Long id) {
		return itemRepository.findById(id).orElseThrow(() ->
			new CustomException(CustomErrorCode.ITEM_NOT_FOUND, null)
		);
	}
}
