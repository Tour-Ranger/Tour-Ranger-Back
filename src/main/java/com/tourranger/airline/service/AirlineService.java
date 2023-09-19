package com.tourranger.airline.service;

import java.net.MalformedURLException;

import org.springframework.core.io.Resource;

public interface AirlineService {
	/**
	 *
	 * @param airlineId airline Id
	 * @return 선택한 상품 id에 해당하는 Airline 이미지 resource
	 */
	String getAirline(Long airlineId);
}



