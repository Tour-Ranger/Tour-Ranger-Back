package com.tourranger.image.airline.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;

import com.tourranger.common.dto.ApiResponseDto;
import com.tourranger.image.airline.dto.AirlineRequestDto;
import com.tourranger.image.airline.dto.AirlineResponseDto;

public interface AirlineService {
	/**
	 *
	 * @param airlineId 상품id
	 * @return 선택한 상품 id에 해당하는 Airline 이미지 resource
	 */
	Resource getAirline(Long airlineId) throws MalformedURLException;

	/**
	 * @param requestDto 추가할 이미지Dto
	 * @return 업로드 된 ImageResponseDto
	 * @throws IOException
	 */
	AirlineResponseDto createAirline(AirlineRequestDto requestDto) throws IOException;

	/**
	 * @param requestDto 변경할 이미지Dto
	 * @return 변경 이미지결과 dto 반환
	 */
	AirlineResponseDto updateAirline(Long airlineId, AirlineRequestDto requestDto) throws IOException;

	/**
	 * @param airlineId 삭제할 airline 이미지id
	 * @return
	 */
	ApiResponseDto deleteAirline(Long airlineId);
}


