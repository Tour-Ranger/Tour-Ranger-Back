package com.tourranger.image.airline.dto;

import com.tourranger.image.airline.entity.Airline;

import lombok.Getter;

@Getter
public class AirlineResponseDto {
	private Long id;
	private String url;

	public AirlineResponseDto(Airline airline) {
		this.id = airline.getId();
		this.url = airline.getUrl();
	}
}
