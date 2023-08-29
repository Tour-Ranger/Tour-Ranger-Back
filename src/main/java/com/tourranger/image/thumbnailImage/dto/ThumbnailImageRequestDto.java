package com.tourranger.image.thumbnailImage.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class ThumbnailImageRequestDto {
	private MultipartFile multipartFile;
	private String url;
}
