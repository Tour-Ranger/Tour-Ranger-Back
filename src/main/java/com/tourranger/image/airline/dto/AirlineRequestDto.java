package com.tourranger.image.airline.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class AirlineRequestDto {
	private String name;
	private MultipartFile multipartFile;
	private String url;
}
