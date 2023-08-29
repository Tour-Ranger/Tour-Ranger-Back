package com.tourranger.item.dto;

import com.tourranger.item.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDto {
	private Long id;
	private String name;
	private Long price;
	private Long discountPrice;
	private Long currentQuantity;
	private Long maxQuantity;
	private String period;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private String travelAgency;
	private String airline;

	public ItemResponseDto(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.price = item.getPrice();
		this.discountPrice = item.getDiscountPrice();
		this.currentQuantity = item.getCurrentQuantity();
		this.maxQuantity = item.getMaxQuantity();
		this.period = item.getPeriod();
		this.departureTime = item.getDepartureTime();
		this.arrivalTime = item.getArrivalTime();
		this.travelAgency = item.getTravelAgency().getName();
		this.airline = item.getAirline().getName();
	}
}
