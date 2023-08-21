package com.tourranger.image.Dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageRequestDto {
	private MultipartFile multipartFile;
}
