package com.tourranger.airline.controller;

import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourranger.airline.service.AirlineService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tour-ranger")
@RequiredArgsConstructor
@Tag(name = "항공사 이미지 관련 API", description = "항공사 이미지 관련 API입니다.")
public class AirlineController {
	private final AirlineService airlineService;

	@GetMapping("/airlines/{airlineId}")
	@Operation(summary = "항공사 이미지파일 조회", description = "항공사 이미지 url의 Resource를 조회합니다.")
	public ResponseEntity<Resource> getAirline(@PathVariable Long airlineId) throws MalformedURLException {
		return ResponseEntity.status(HttpStatus.OK).body(airlineService.getAirline(airlineId));
	}

}
