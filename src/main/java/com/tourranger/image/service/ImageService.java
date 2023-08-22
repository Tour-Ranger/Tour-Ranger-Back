package com.tourranger.image.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.tourranger.common.dto.ApiResponseDto;
import com.tourranger.image.Dto.ImageResponseDto;

public interface ImageService {

	/**
	 *
	 * @param imageId 이미지id
	 * @return 선택한 id에 해당하는 이미지 resource
	 */
	Resource getImage(Long imageId) throws MalformedURLException;

	/**
	 * @param multipartFile 이미지 파일
	 * @return 생성 이미지 dto 반환
	 */
	ImageResponseDto createImage(MultipartFile multipartFile) throws IOException;

	/**
	 * @param multipartFile 이미지파일
	 * @return 변경 이미지결과 dto 반환
	 */
	ImageResponseDto updateImage(Long imageId, MultipartFile multipartFile) throws IOException;

	/**
	 * @param imageId 이미지id
	 * @return 삭제여부
	 */
	ApiResponseDto deleteImage(Long imageId);

	List<ImageResponseDto> getImageList();
}
