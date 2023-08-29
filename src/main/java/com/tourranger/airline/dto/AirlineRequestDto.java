package com.tourranger.airline.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirlineRequestDto {
	private String name;
	private MultipartFile multipartFile;
	private String url;
}
