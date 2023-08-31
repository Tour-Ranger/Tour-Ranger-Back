package com.tourranger.item.service;

import com.tourranger.common.error.CustomErrorCode;
import com.tourranger.common.exception.CustomException;
import com.tourranger.item.dto.ItemResponseDto;
import com.tourranger.item.entity.Item;
import com.tourranger.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;
	@Override
	public ItemResponseDto getItem(String itemName) {
		Item item = findItem(itemName);
		ItemResponseDto responseDto = new ItemResponseDto(item);
		return responseDto;
	}

	public Item findItem(String name) {
		return itemRepository.findByName(name).orElseThrow(() ->
				new CustomException(CustomErrorCode.ITEM_NOT_FOUND, null)
		);
	}

}
