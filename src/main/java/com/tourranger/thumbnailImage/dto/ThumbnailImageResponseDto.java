package com.tourranger.thumbnailImage.dto;

import com.tourranger.thumbnailImage.entity.ThumbnailImage;

import lombok.Getter;

@Getter
public class ThumbnailImageResponseDto {
	private Long id;
	private String url;

	public ThumbnailImageResponseDto(ThumbnailImage thumbnailImage) {
		this.id = thumbnailImage.getId();
		this.url = thumbnailImage.getUrl();
	}
}
