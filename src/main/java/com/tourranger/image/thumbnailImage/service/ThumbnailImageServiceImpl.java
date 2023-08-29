package com.tourranger.image.thumbnailImage.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tourranger.common.dto.ApiResponseDto;
import com.tourranger.common.error.CustomErrorCode;
import com.tourranger.common.exception.CustomException;
import com.tourranger.common.file.FileStore;
import com.tourranger.image.thumbnailImage.dto.ThumbnailImageRequestDto;
import com.tourranger.image.thumbnailImage.dto.ThumbnailImageResponseDto;
import com.tourranger.image.thumbnailImage.entity.ThumbnailImage;
import com.tourranger.image.thumbnailImage.repository.ThumbnailImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThumbnailImageServiceImpl implements ThumbnailImageService {
	private final ThumbnailImageRepository thumbnailImageRepository;
	private final FileStore fileStore;

	//이미지를 조회하는 메서드
	@Override
	@Transactional(readOnly = true)
	public Resource getThumbnailImage(Long thumbnailImageId) throws MalformedURLException {
		ThumbnailImage thumbnailImage = findThumbnailImage(thumbnailImageId);
		return new UrlResource(thumbnailImage.getUrl());
	}

	//이미지를 추가하는 메서드 - MultipartFile 타입
	@Override
	@Transactional
	public ThumbnailImageResponseDto createThumbnailImage(ThumbnailImageRequestDto requestDto) throws IOException {
		ThumbnailImage thumbnailImage;
		//이미지 파일 저장 방식인 경우
		if (requestDto.getUrl()==null && requestDto.getMultipartFile() != null) {
			//이미지 파일을 지정한 경로에 저장
			String saveFileName = fileStore.saveFile(requestDto.getMultipartFile());

			//DB에 thumbnailImage 객체 저장
			thumbnailImage = new ThumbnailImage(saveFileName);
			thumbnailImageRepository.save(thumbnailImage);

			//thumbnailImageResponseDto로 결과 반환
			return new ThumbnailImageResponseDto(thumbnailImage);
		}
		//url 저장 방식인 경우
		else if (requestDto.getMultipartFile() == null && requestDto.getUrl()!=null) {
			thumbnailImage = new ThumbnailImage(requestDto.getUrl());
			thumbnailImageRepository.save(thumbnailImage);
			//thumbnailImageResponseDto로 결과 반환
			return new ThumbnailImageResponseDto(thumbnailImage);
		}
		//imageFile과 url 둘 다 비었거나, 둘 다 입력된 경우 Exception
		else
			throw new CustomException(CustomErrorCode.UPLOAD_NOT_FOUND, null);
	}

	//선택한 이미지 변경하기 - 이미지 첨부형식
	@Transactional
	public ThumbnailImageResponseDto updateThumbnailImage(Long thumbnailImageId,
		ThumbnailImageRequestDto requestDto) throws
		IOException {
		ThumbnailImage targetThumbnailImage = findThumbnailImage(thumbnailImageId);
		//수정할 Thumbnail객체 이미지가 파일형식으로 저장된 경우
		if (targetThumbnailImage.getUrl().contains("file:")) {
			//기존 이미지파일은 삭제
			fileStore.deleteFile(targetThumbnailImage.getUrl());
		}

		//이미지 파일 저장 방식인 경우
		if (requestDto.getUrl()==null && requestDto.getMultipartFile()!=null) {
			//새로 업로드한 이미지파일 저장
			String saveFileName = fileStore.saveFile(requestDto.getMultipartFile());
			//저장경로로 url 변경
			targetThumbnailImage.setUrl(saveFileName);
		}
		//url 저장 방식인 경우
		else if (requestDto.getMultipartFile()!=null && requestDto.getUrl()!=null) {
			//url 변경
			targetThumbnailImage.setUrl(requestDto.getUrl());
		}
		//imageFile과 url 둘 다 비었거나, 둘 다 입력된 경우 Exception
		else
			throw new CustomException(CustomErrorCode.UPLOAD_NOT_FOUND, null);

		return new ThumbnailImageResponseDto(targetThumbnailImage);
	}

	@Override
	@Transactional
	public ApiResponseDto deleteThumbnailImage(Long thumbnailImageId) {
		ThumbnailImage targetThumbnailImage = findThumbnailImage(thumbnailImageId);
		//저장소에서 이미지 찾아 삭제
		fileStore.deleteFile(targetThumbnailImage.getUrl());
		//DB에서 이미지 정보 삭제
		thumbnailImageRepository.deleteById(targetThumbnailImage.getId());

		return new ApiResponseDto(200, "이미지 삭제 완료");
	}

	// 이미지 객체를 repository에서 찾는 메서드
	// id로 조회했을 때, 존재하지 않는 이미지인 경우 Exception 발생
	private ThumbnailImage findThumbnailImage(Long thumbnailImageId) {
		return thumbnailImageRepository.findById(thumbnailImageId).orElseThrow(() ->
			new CustomException(CustomErrorCode.IMAGE_NOT_FOUND, null)

		);
	}
}
