package com.tourranger.travelAgency.service;

import java.net.MalformedURLException;

import org.springframework.core.io.Resource;

public interface TravelAgencyService {
	/**
	 *
	 * @param travelAgencyName travel Agency 이름
	 * @return 선택한 상품 id에 해당하는 travelAgency 이미지 resource
	 */
	Resource getTravelAgency(String travelAgencyName) throws MalformedURLException;
}