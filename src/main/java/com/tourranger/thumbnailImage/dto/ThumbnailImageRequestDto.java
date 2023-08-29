package com.tourranger.thumbnailImage.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThumbnailImageRequestDto {
	private MultipartFile multipartFile;
	private String url;
}
