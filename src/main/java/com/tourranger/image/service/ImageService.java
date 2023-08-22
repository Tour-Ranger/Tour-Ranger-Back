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
	 * @param multipartFileList 상품에 등록할 이미지 파일 리스트
	 * @return 추가된 이미지 dto리스트 반환
	 */
	List<ImageResponseDto> createImage(Long itemId, List<MultipartFile> multipartFileList) throws IOException;

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

	/**
	 *
	 * @param itemId 상품id
	 * @return 상품에 속한 imageList
	 */
	List<ImageResponseDto> getImageList(Long itemId);
}
