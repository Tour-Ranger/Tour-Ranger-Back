package com.tourranger.image.travelAgency.dto;

import com.tourranger.image.travelAgency.entity.TravelAgency;

import lombok.Getter;

@Getter
public class TravelAgencyResponseDto {
	private Long id;
	private String url;

	public TravelAgencyResponseDto(TravelAgency travelAgency) {
		this.id = travelAgency.getId();
		this.url = travelAgency.getUrl();
	}
}
