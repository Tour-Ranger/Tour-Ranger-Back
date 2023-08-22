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
import com.tourranger.common.file.FileStore;
import com.tourranger.image.Dto.ImageResponseDto;
import com.tourranger.image.entity.Image;
import com.tourranger.image.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
	private final ImageRepository imageRepository;
	private final FileStore fileStore;

	//이미지를 조회하는 메서드
	@Override
	@Transactional(readOnly = true)
	public Resource getImage(Long imageId) throws MalformedURLException {
		Image targetImage = findImage(imageId);
		return new UrlResource("file:"+fileStore.getFullPath(targetImage.getUrl()));
	}

	//이미지 전체를 조회하는 메서드
	@Override
	@Transactional(readOnly = true)
	public List<ImageResponseDto> getImageList() {
		return imageRepository.findAll().stream().map(ImageResponseDto::new).toList();
	}

	//이미지를 추가하는 메서드
	@Override
	@Transactional
	public ImageResponseDto createImage(MultipartFile multipartFile) throws IOException {
		//이미지 파일을 지정한 경로에 저장
		String saveFileName = fileStore.saveFile(multipartFile);
		//DB에 정보 저장
		Image image = new Image(saveFileName);
		imageRepository.save(image);
		return new ImageResponseDto(image);
	}

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
	// id로 조회했을 때, 존재하지 않는 이미지인 경우 IllegalException 발생
	private Image findImage(Long imageId) {
		return imageRepository.findById(imageId).orElseThrow(() ->
			new IllegalArgumentException("선택한 id의 이미지는 존재하지 않습니다.")
		);
	}




}
