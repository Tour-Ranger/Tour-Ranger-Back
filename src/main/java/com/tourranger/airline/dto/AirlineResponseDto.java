package com.tourranger.airline.dto;

import com.tourranger.airline.entity.Airline;

import lombok.Getter;

@Getter
public class AirlineResponseDto {
	private Long id;
	private String name;
	private String url;

	public AirlineResponseDto(Airline airline) {
		this.id = airline.getId();
		this.name = airline.getName();
		this.url = airline.getUrl();
	}
}
