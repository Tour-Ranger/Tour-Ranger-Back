package com.tourranger.image.travelAgency.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class TravelAgencyRequestDto {
	private String name;
	private MultipartFile multipartFile;
	private String url;
}