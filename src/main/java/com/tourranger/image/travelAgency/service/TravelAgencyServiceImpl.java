package com.tourranger.image.travelAgency.service;

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
import com.tourranger.image.travelAgency.dto.TravelAgencyRequestDto;
import com.tourranger.image.travelAgency.dto.TravelAgencyResponseDto;
import com.tourranger.image.travelAgency.entity.TravelAgency;
import com.tourranger.image.travelAgency.repository.TravelAgencyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelAgencyServiceImpl implements TravelAgencyService {
	private final TravelAgencyRepository travelAgencyRepository;
	private final FileStore fileStore;

	//이미지를 조회하는 메서드
	@Override
	@Transactional(readOnly = true)
	public Resource getTravelAgency(Long travelAgencyId) throws MalformedURLException {
		TravelAgency travelAgency = findTravelAgency(travelAgencyId);
		return new UrlResource(travelAgency.getUrl());
	}

	//이미지를 추가하는 메서드 - MultipartFile 타입
	@Override
	@Transactional
	public TravelAgencyResponseDto createTravelAgency(TravelAgencyRequestDto requestDto) throws IOException {
		TravelAgency travelAgency;
		//이미지 파일 저장 방식인 경우
		if (requestDto.getUrl().isEmpty() && !requestDto.getMultipartFile().isEmpty()) {
			//이미지 파일을 지정한 경로에 저장
			String saveFileName = fileStore.saveFile(requestDto.getMultipartFile());

			//DB에 TravelAgency 객체 저장
			travelAgency = new TravelAgency(requestDto.getName(), saveFileName);
			travelAgencyRepository.save(travelAgency);

			//TravelAgencyResponseDto로 결과 반환
			return new TravelAgencyResponseDto(travelAgency);
		}
		//url 저장 방식인 경우
		else if (requestDto.getMultipartFile().isEmpty() && !requestDto.getUrl().isEmpty()) {
			travelAgency = new TravelAgency(requestDto.getName(), requestDto.getUrl());
			travelAgencyRepository.save(travelAgency);
			//TravelAgencyResponseDto로 결과 반환
			return new TravelAgencyResponseDto(travelAgency);
		}
		//imageFile과 url 둘 다 비었거나, 둘 다 입력된 경우 Exception
		else
			throw new CustomException(CustomErrorCode.UPLOAD_NOT_FOUND, null);
	}

	//선택한 이미지 변경하기 - 이미지 첨부형식
	@Transactional
	public TravelAgencyResponseDto updateTravelAgency(Long travelAgencyId, TravelAgencyRequestDto requestDto) throws
		IOException {
		TravelAgency targetTravelAgency = findTravelAgency(travelAgencyId);
		//수정할 TravelAgency객체 이미지가 파일형식으로 저장된 경우
		if (targetTravelAgency.getUrl().contains("file:")) {
			//기존 이미지파일은 삭제
			fileStore.deleteFile(targetTravelAgency.getUrl());
		}

		//이미지 파일 저장 방식인 경우
		if (requestDto.getUrl().isEmpty() && !requestDto.getMultipartFile().isEmpty()) {
			//새로 업로드한 이미지파일 저장
			String saveFileName = fileStore.saveFile(requestDto.getMultipartFile());
			//저장경로로 url 변경
			targetTravelAgency.setUrl(saveFileName);
		}
		//url 저장 방식인 경우
		else if (requestDto.getMultipartFile().isEmpty() && !requestDto.getUrl().isEmpty()) {
			//url 변경
			targetTravelAgency.setUrl(requestDto.getUrl());
		}
		//imageFile과 url 둘 다 비었거나, 둘 다 입력된 경우 Exception
		else
			throw new CustomException(CustomErrorCode.UPLOAD_NOT_FOUND, null);

		return new TravelAgencyResponseDto(targetTravelAgency);
	}

	@Override
	@Transactional
	public ApiResponseDto deleteTravelAgency(Long travelAgencyId) {
		TravelAgency targetTravelAgency = findTravelAgency(travelAgencyId);
		//저장소에서 이미지 찾아 삭제
		fileStore.deleteFile(targetTravelAgency.getUrl());
		//DB에서 이미지 정보 삭제
		travelAgencyRepository.deleteById(targetTravelAgency.getId());

		return new ApiResponseDto(200, "이미지 삭제 완료");
	}

	// 이미지 객체를 repository에서 찾는 메서드
	// id로 조회했을 때, 존재하지 않는 이미지인 경우 Exception 발생
	private TravelAgency findTravelAgency(Long travelAgencyId) {
		return travelAgencyRepository.findById(travelAgencyId).orElseThrow(() ->
			new CustomException(CustomErrorCode.IMAGE_NOT_FOUND, null)

		);
	}
}
