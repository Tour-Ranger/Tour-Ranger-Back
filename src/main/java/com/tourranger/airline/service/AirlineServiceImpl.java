package com.tourranger.airline.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tourranger.airline.entity.Airline;
import com.tourranger.airline.repository.AirlineRepository;
import com.tourranger.common.error.CustomErrorCode;
import com.tourranger.common.exception.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {
	private final AirlineRepository airlineRepository;

	//이미지를 조회하는 메서드
	@Override
	@Transactional(readOnly = true)
	public String getAirline(Long airlineId) {
		Airline airline = findAirline(airlineId);
		return airline.getUrl();
	}

	// 이미지 객체를 repository에서 찾는 메서드
	// id로 조회했을 때, 존재하지 않는 이미지인 경우 Exception 발생
	private Airline findAirline(Long airlineId) {
		return airlineRepository.findById(airlineId).orElseThrow(() ->
			new CustomException(CustomErrorCode.IMAGE_NOT_FOUND, null)
		);
	}
}
