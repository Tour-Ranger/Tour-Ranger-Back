package com.tourranger.image.Dto;

import com.tourranger.image.entity.Image;

import lombok.Getter;

@Getter
public class ImageResponseDto {
	private Long id;
	private String url;

	public ImageResponseDto(Image image) {
		this.id = image.getId();
		this.url = image.getUrl();
	}
}
