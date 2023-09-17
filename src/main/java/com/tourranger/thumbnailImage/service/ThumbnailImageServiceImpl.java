package com.tourranger.thumbnailImage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tourranger.common.error.CustomErrorCode;
import com.tourranger.common.exception.CustomException;
import com.tourranger.thumbnailImage.entity.ThumbnailImage;
import com.tourranger.thumbnailImage.repository.ThumbnailImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThumbnailImageServiceImpl implements ThumbnailImageService {
	private final ThumbnailImageRepository thumbnailImageRepository;

	//이미지를 조회하는 메서드
	@Override
	@Transactional(readOnly = true)
	public String getThumbnailImage(Long thumbnailImageId) {
		ThumbnailImage thumbnailImage = findThumbnailImage(thumbnailImageId);
		return thumbnailImage.getUrl();
	}

	// 이미지 객체를 repository에서 찾는 메서드
	// id로 조회했을 때, 존재하지 않는 이미지인 경우 Exception 발생
	private ThumbnailImage findThumbnailImage(Long thumbnailImageId) {
		return thumbnailImageRepository.findById(thumbnailImageId).orElseThrow(() ->
			new CustomException(CustomErrorCode.IMAGE_NOT_FOUND, null)

		);
	}
}
