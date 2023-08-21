package com.tourranger.image.service;

import com.tourranger.common.dto.ApiResponseDto;
import com.tourranger.image.Dto.ImageResponseDto;

public interface ImageService {

	/**
	 *
	 * @param imageId 이미지id
	 * @return 선택한 id에 해당하는 이미지
	 */
	ImageResponseDto getImage(Long imageId);

	/**
	 * @param url 이미지url
	 * @return 생성 이미지 결과
	 */
	ImageResponseDto createImage(String url);

	/**
	 * @param url 이미지url
	 * @return 변경 이미지 결과
	 */
	ImageResponseDto updateImage(Long imageId, String url);

	/**
	 * @param imageId 이미지id
	 * @return 삭제여부
	 */
	ApiResponseDto deleteImage(Long imageId);
}
