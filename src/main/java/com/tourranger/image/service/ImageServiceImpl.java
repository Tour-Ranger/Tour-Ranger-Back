package com.tourranger.image.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tourranger.common.dto.ApiResponseDto;
import com.tourranger.common.error.CustomErrorCode;
import com.tourranger.common.exception.CustomException;
import com.tourranger.common.file.FileStore;
import com.tourranger.image.Dto.ImageResponseDto;
import com.tourranger.image.entity.Image;
import com.tourranger.image.repository.ImageRepository;
import com.tourranger.item.entity.Item;
import com.tourranger.item.service.ItemServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
	private final ImageRepository imageRepository;
	private final ItemServiceImpl itemService;
	private final FileStore fileStore;

	//이미지를 조회하는 메서드
	@Override
	@Transactional(readOnly = true)
	public Resource getImage(Long imageId) throws MalformedURLException {
		Image targetImage = findImage(imageId);
		return new UrlResource(targetImage.getUrl());
	}

	//아이템에 속한 이미지 전체를 조회하는 메서드
	@Override
	@Transactional(readOnly = true)
	public List<ImageResponseDto> getImageList(Long itemId) {
		//itemId로 상품 찾기
		Item item = itemService.findItem(itemId);
		//상품에 속한 이미지 전체 반환
		return imageRepository.findAllByItem(item).stream().map(ImageResponseDto::new).toList();
	}

	//상품에 이미지(여러 개)를 추가하는 메서드
	@Override
	@Transactional
	public List<ImageResponseDto> createImage(Long itemId, List<MultipartFile> multipartFileList) throws IOException {
		//이미지 등록할 상품
		Item item = itemService.findItem(itemId);
		//이미지 파일을 지정한 경로에 저장
		List<String> saveFileNameList = fileStore.saveFiles(multipartFileList);

		//DB에 정보 저장
		//이미지 객체 리스트 생성
		List<Image> images = saveFileNameList.stream()
			.map(saveFileName -> new Image(item, saveFileName)) //SaveFileName을 토대로 이미지객체로 변환
			.toList();
		//모두 저장
		imageRepository.saveAll(images);

		//List<ImageResponseDto>로 결과 반환
		return images.stream()
			.map(ImageResponseDto::new)
			.toList();
	}

	//선택한 이미지 변경하기
	@Override
	@Transactional
	public ImageResponseDto updateImage(Long imageId, MultipartFile multipartFile) throws IOException {
		Image targetImage = findImage(imageId);

		//기존 이미지파일은 삭제
		fileStore.deleteFile(targetImage.getUrl());
		//새로 변경한 이미지파일 저장
		String saveFileName = fileStore.saveFile(multipartFile);
		//변경한 url에 맞게 이미지 변경
		targetImage.setUrl(saveFileName);

		return new ImageResponseDto(targetImage);
	}

	//선택한 이미지를 삭제하는 메서드
	@Override
	@Transactional
	public ApiResponseDto deleteImage(Long imageId) {
		Image targetImage = findImage(imageId);
		//서버에서 이미지 찾아 삭제
		fileStore.deleteFile(targetImage.getUrl());
		//DB에서 이미지 정보 삭제
		imageRepository.delete(targetImage);

		return new ApiResponseDto(200, "이미지 삭제 완료");
	}

	// 이미지 객체를 repository에서 찾는 메서드
	// id로 조회했을 때, 존재하지 않는 이미지인 경우 Exception 발생
	private Image findImage(Long imageId) {
		return imageRepository.findById(imageId).orElseThrow(() ->
			new CustomException(CustomErrorCode.IMAGE_NOT_FOUND, null)

		);
	}

}
