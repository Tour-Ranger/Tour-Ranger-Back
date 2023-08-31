package com.tourranger.airline.service;

import java.net.MalformedURLException;

import org.springframework.core.io.Resource;

public interface AirlineService {
	/**
	 *
	 * @param airlineName airline 이름
	 * @return 선택한 상품 id에 해당하는 Airline 이미지 resource
	 */
	Resource getAirline(String airlineName) throws MalformedURLException;
}



