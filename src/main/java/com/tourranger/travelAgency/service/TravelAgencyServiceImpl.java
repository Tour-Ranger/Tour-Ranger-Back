package com.tourranger.travelAgency.service;

import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tourranger.common.error.CustomErrorCode;
import com.tourranger.common.exception.CustomException;
import com.tourranger.common.file.FileStore;
import com.tourranger.travelAgency.entity.TravelAgency;

import lombok.RequiredArgsConstructor;

import com.tourranger.travelAgency.repository.TravelAgencyRepository;

@Service
@RequiredArgsConstructor
public class TravelAgencyServiceImpl implements TravelAgencyService {
	private final TravelAgencyRepository travelAgencyRepository;
	private final FileStore fileStore;

	//이미지를 조회하는 메서드
	@Override
	@Transactional(readOnly = true)
	public Resource getTravelAgency(String travelAgencyName) throws MalformedURLException {
		TravelAgency travelAgency = findTravelAgency(travelAgencyName);
		return new UrlResource(travelAgency.getUrl());
	}

	// 이미지 객체를 repository에서 찾는 메서드
	// id로 조회했을 때, 존재하지 않는 이미지인 경우 Exception 발생
	private TravelAgency findTravelAgency(String name) {
		return travelAgencyRepository.findTopByName(name).orElseThrow(() ->
			new CustomException(CustomErrorCode.IMAGE_NOT_FOUND, null)

		);
	}
}
