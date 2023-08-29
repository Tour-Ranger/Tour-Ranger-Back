package com.tourranger.image.airline.service;

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
import com.tourranger.image.airline.dto.AirlineRequestDto;
import com.tourranger.image.airline.dto.AirlineResponseDto;
import com.tourranger.image.airline.entity.Airline;
import com.tourranger.image.airline.repository.AirlineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {
	private final AirlineRepository airlineRepository;
	private final FileStore fileStore;

	//이미지를 조회하는 메서드
	@Override
	@Transactional(readOnly = true)
	public Resource getAirline(Long airlineId) throws MalformedURLException {
		Airline airline = findAirline(airlineId);
		return new UrlResource(airline.getUrl());
	}

	//이미지를 추가하는 메서드 - MultipartFile 타입
	@Override
	@Transactional
	public AirlineResponseDto createAirline(AirlineRequestDto requestDto) throws IOException {
		Airline airline;
		//이미지 파일 저장 방식인 경우
		if (requestDto.getUrl().isEmpty() && !requestDto.getMultipartFile().isEmpty()) {
			//이미지 파일을 지정한 경로에 저장
			String saveFileName = fileStore.saveFile(requestDto.getMultipartFile());

			//DB에 airline 객체 저장
			airline = new Airline(requestDto.getName(), saveFileName);
			airlineRepository.save(airline);

			//AirlineResponseDto로 결과 반환
			return new AirlineResponseDto(airline);
		}
		//url 저장 방식인 경우
		else if (requestDto.getMultipartFile().isEmpty() && !requestDto.getUrl().isEmpty()) {
			airline = new Airline(requestDto.getName(), requestDto.getUrl());
			airlineRepository.save(airline);
			//AirlineResponseDto로 결과 반환
			return new AirlineResponseDto(airline);
		}
		//imageFile과 url 둘 다 비었거나, 둘 다 입력된 경우 Exception
		else
			throw new CustomException(CustomErrorCode.UPLOAD_NOT_FOUND, null);
	}

	//선택한 이미지 변경하기 - 이미지 첨부형식
	@Override
	@Transactional
	public AirlineResponseDto updateAirline(Long airlineId, AirlineRequestDto requestDto) throws IOException {
		Airline targetAirline = findAirline(airlineId);
		//수정할 Airline객체 이미지가 파일형식으로 저장된 경우
		if (targetAirline.getUrl().contains("file:")) {
			//기존 이미지파일은 삭제
			fileStore.deleteFile(targetAirline.getUrl());
		}

		//이미지 파일 저장 방식인 경우
		if (requestDto.getUrl().isEmpty() && !requestDto.getMultipartFile().isEmpty()) {
			//새로 업로드한 이미지파일 저장
			String saveFileName = fileStore.saveFile(requestDto.getMultipartFile());
			//저장경로로 url 변경
			targetAirline.setUrl(saveFileName);
		}
		//url 저장 방식인 경우
		else if (requestDto.getMultipartFile().isEmpty() && !requestDto.getUrl().isEmpty()) {
			//url 변경
			targetAirline.setUrl(requestDto.getUrl());
		}
		//imageFile과 url 둘 다 비었거나, 둘 다 입력된 경우 Exception
		else
			throw new CustomException(CustomErrorCode.UPLOAD_NOT_FOUND, null);

		return new AirlineResponseDto(targetAirline);
	}

	@Override
	@Transactional
	public ApiResponseDto deleteAirline(Long airlineId) {
		Airline targetAirline = findAirline(airlineId);
		//저장소에서 이미지 찾아 삭제
		fileStore.deleteFile(targetAirline.getUrl());
		//DB에서 이미지 정보 삭제
		airlineRepository.deleteById(targetAirline.getId());

		return new ApiResponseDto(200, "이미지 삭제 완료");
	}

	// 이미지 객체를 repository에서 찾는 메서드
	// id로 조회했을 때, 존재하지 않는 이미지인 경우 Exception 발생
	private Airline findAirline(Long airlineId) {
		return airlineRepository.findById(airlineId).orElseThrow(() ->
			new CustomException(CustomErrorCode.IMAGE_NOT_FOUND, null)

		);
	}
}
