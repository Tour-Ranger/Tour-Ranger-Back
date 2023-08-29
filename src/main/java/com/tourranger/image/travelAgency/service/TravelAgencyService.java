package com.tourranger.image.travelAgency.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;

import com.tourranger.common.dto.ApiResponseDto;
import com.tourranger.image.travelAgency.dto.TravelAgencyRequestDto;
import com.tourranger.image.travelAgency.dto.TravelAgencyResponseDto;

public interface TravelAgencyService {
	/**
	 *
	 * @param travelAgencyId 상품id
	 * @return 선택한 상품 id에 해당하는 travelAgency 이미지 resource
	 */
	Resource getTravelAgency(Long travelAgencyId) throws MalformedURLException;

	/**
	 * @param requestDto 추가할 이미지Dto
	 * @return 업로드 된 TravelAgencyResponseDto
	 * @throws IOException
	 */
	TravelAgencyResponseDto createTravelAgency(TravelAgencyRequestDto requestDto) throws IOException;

	/**
	 * @param requestDto 변경할 이미지Dto
	 * @return 변경 이미지결과 dto 반환
	 */
	TravelAgencyResponseDto updateTravelAgency(Long travelAgencyId, TravelAgencyRequestDto requestDto) throws IOException;

	/**
	 * @param travelAgencyId 삭제할 airline 이미지id
	 * @return
	 */
	ApiResponseDto deleteTravelAgency(Long travelAgencyId);
}


