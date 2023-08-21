package com.tourranger.image.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tourranger.common.dto.ApiResponseDto;
import com.tourranger.image.Dto.ImageResponseDto;
import com.tourranger.image.entity.Image;
import com.tourranger.image.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {
	ImageRepository imageRepository;

	public ImageServiceImpl(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	//이미지를 조회하는 메서드
	@Override
	@Transactional(readOnly = true)
	public ImageResponseDto getImage(Long imageId) {
		Image targetImage = findImage(imageId);
		return new ImageResponseDto(targetImage);
	}

	//이미지를 추가하는 메서드
	@Override
	@Transactional
	public ImageResponseDto createImage(String url) {
		Image image = new Image(url);
		imageRepository.save(image);
		return new ImageResponseDto(image);
	}

	//선택한 이미지의 url변경 메서드
	@Override
	@Transactional
	public ImageResponseDto updateImage(Long imageId, String url) {
		Image targetImage = findImage(imageId);
		targetImage.setUrl(url);

		return new ImageResponseDto(targetImage);
	}

	//선택한 이미지를 삭제하는 메서드
	@Override
	@Transactional
	public ApiResponseDto deleteImage(Long imageId) {
		Image targetImage = findImage(imageId);
		imageRepository.delete(targetImage);

		return new ApiResponseDto(200, "이미지 삭제 완료");
	}

	// 이미지 객체를 repository에서 찾는 메서드
	// id로 조회했을 때, 존재하지 않는 이미지인 경우 IllegalException 발생
	private Image findImage(Long imageId) {
		return imageRepository.findById(imageId).orElseThrow(() ->
			new IllegalArgumentException("선택한 id의 이미지는 존재하지 않습니다.")
		);
	}
}
