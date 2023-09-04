package com.tourranger.item.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tourranger.common.error.CustomErrorCode;
import com.tourranger.common.exception.CustomException;
import com.tourranger.item.dto.ItemResponseDto;
import com.tourranger.item.entity.Item;
import com.tourranger.item.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;

	@Override
	public ItemResponseDto getItem(Long itemId) {
		Item item = findItem(itemId);
		ItemResponseDto responseDto = new ItemResponseDto(item);
		return responseDto;
	}

	@Override
	@Cacheable(key = "#pageable.pageNumber", value = "ItemList")
	public List<ItemResponseDto> getItemList(Pageable pageable) {
		return itemRepository.findAllByOrderById(pageable).stream().map(ItemResponseDto::new).toList();
	}

	@Override
	public List<ItemResponseDto> getSearchedItemList(String search, String condition, Pageable pageable) {

		return switch (condition) {
			case "latest" -> // 최신순
				itemRepository.findByNameContainingOrderByIdDesc(search, pageable)
					.stream()
					.map(ItemResponseDto::new)
					.toList();
			case "priceLowToHigh" -> // 가격 낮은순
				itemRepository.findByNameContainingOrderByDiscountPrice(search, pageable)
					.stream()
					.map(ItemResponseDto::new)
					.toList();
			case "priceHighToLow" -> // 가격 높은순
				itemRepository.findByNameContainingOrderByDiscountPriceDesc(search, pageable)
					.stream()
					.map(ItemResponseDto::new)
					.toList();
			default -> null;
		};
	}

	public Item findItem(Long id) {
		return itemRepository.findById(id).orElseThrow(() -> new CustomException(CustomErrorCode.ITEM_NOT_FOUND, null));
	}


}
