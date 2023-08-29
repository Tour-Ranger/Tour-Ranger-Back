package com.tourranger.image.thumbnailImage.dto;
import com.tourranger.image.thumbnailImage.entity.ThumbnailImage;

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
