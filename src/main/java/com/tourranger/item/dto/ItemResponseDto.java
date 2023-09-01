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
	private Long travelAgencyId;
	private String travelAgencyName;
	private Long airlineId;
	private String airlineName;
	private Long thumbnailImageId;

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
		this.travelAgencyId = item.getTravelAgency().getId();
		this.travelAgencyName = item.getTravelAgency().getName();
		this.airlineId = item.getAirline().getId();
		this.airlineName = item.getAirline().getName();
		this.thumbnailImageId = item.getThumbnailImage().getId();
	}
}
