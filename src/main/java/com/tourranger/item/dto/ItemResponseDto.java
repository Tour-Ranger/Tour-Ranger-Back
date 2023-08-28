package com.tourranger.item.dto;

import com.tourranger.item.entity.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDto {
	private Long id;
	private String name;
	private Long price;
	private Long discountPrice;
	private Long quantity;
	private Long period;

	public ItemResponseDto(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.price = item.getPrice();
		this.discountPrice = item.getDiscountPrice();
		this.quantity = item.getQuantity();
		this.period = item.getPeriod();
	}
}
