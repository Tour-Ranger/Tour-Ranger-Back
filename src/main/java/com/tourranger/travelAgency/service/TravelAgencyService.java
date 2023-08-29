package com.tourranger.travelAgency.service;

import java.net.MalformedURLException;

import org.springframework.core.io.Resource;

public interface TravelAgencyService {
	/**
	 *
	 * @param travelAgencyId 상품id
	 * @return 선택한 상품 id에 해당하는 travelAgency 이미지 resource
	 */
	Resource getTravelAgency(Long travelAgencyId) throws MalformedURLException;
}


