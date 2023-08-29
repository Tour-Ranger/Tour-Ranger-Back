package com.tourranger.thumbnailImage.service;

import java.net.MalformedURLException;

import org.springframework.core.io.Resource;

public interface ThumbnailImageService {
	/**
	 *
	 * @param thumbnailImageId
	 * @return 선택한 상품 id에 해당하는 Airline 이미지 resource
	 */
	Resource getThumbnailImage(Long thumbnailImageId) throws MalformedURLException;
}


