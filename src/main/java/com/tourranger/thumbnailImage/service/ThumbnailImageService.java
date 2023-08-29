package com.tourranger.thumbnailImage.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;

import com.tourranger.common.dto.ApiResponseDto;
import com.tourranger.thumbnailImage.dto.ThumbnailImageRequestDto;
import com.tourranger.thumbnailImage.dto.ThumbnailImageResponseDto;

public interface ThumbnailImageService {
	/**
	 *
	 * @param thumbnailImageId
	 * @return 선택한 상품 id에 해당하는 Airline 이미지 resource
	 */
	Resource getThumbnailImage(Long thumbnailImageId) throws MalformedURLException;

	/**
	 * @param requestDto 추가할 이미지Dto
	 * @return 업로드 된 ImageResponseDto
	 * @throws IOException
	 */
	ThumbnailImageResponseDto createThumbnailImage(ThumbnailImageRequestDto requestDto) throws IOException;

	/**
	 * @param requestDto 변경할 이미지Dto
	 * @return 변경 이미지결과 dto 반환
	 */
	ThumbnailImageResponseDto updateThumbnailImage(Long thumbnailImageId, ThumbnailImageRequestDto requestDto) throws
		IOException;

	/**
	 * @param thumbnailImageId 삭제할 airline 이미지id
	 * @return
	 */
	ApiResponseDto deleteThumbnailImage(Long thumbnailImageId);
}


