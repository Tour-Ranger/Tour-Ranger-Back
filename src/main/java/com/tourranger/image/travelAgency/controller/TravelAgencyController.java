package com.tourranger.image.travelAgency.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourranger.common.dto.ApiResponseDto;
import com.tourranger.image.travelAgency.dto.TravelAgencyRequestDto;
import com.tourranger.image.travelAgency.dto.TravelAgencyResponseDto;
import com.tourranger.image.travelAgency.service.TravelAgencyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tour-ranger")
@RequiredArgsConstructor
@Tag(name = "여행사 이미지 관련 API", description = "여행사 이미지 관련 API입니다.")
public class TravelAgencyController {
	private final TravelAgencyService travelAgencyService;

	@GetMapping("/travelAgencies/{travelAgencyId}")
	@Operation(summary = "여행사 이미지파일 조회", description = "여행사 이미지 url의 Resource를 조회합니다.")
	public ResponseEntity<Resource> getTravelAgency(@PathVariable Long travelAgencyId) throws MalformedURLException {
		return ResponseEntity.status(HttpStatus.OK).body(travelAgencyService.getTravelAgency(travelAgencyId));
	}

	@PutMapping("/travelAgencies/{travelAgencyId}")
	@Operation(summary = "여행사 이미지파일 변경", description = "여행사 이미지를 변경합니다.")
	public ResponseEntity<TravelAgencyResponseDto> updateTravelAgency(@PathVariable Long travelAgencyId,
		@ModelAttribute TravelAgencyRequestDto requestDto) throws IOException {
		return ResponseEntity.status(HttpStatus.OK)
			.body(travelAgencyService.updateTravelAgency(travelAgencyId, requestDto));
	}

	@PostMapping(value = "/travelAgencies")
	@Operation(summary = "여행사 이미지파일 업로드", description = "여행사 이미지를 저장합니다.")
	public ResponseEntity<TravelAgencyResponseDto> createTravelAgency(
		@ModelAttribute TravelAgencyRequestDto requestDto) throws IOException {
		return ResponseEntity.status(HttpStatus.CREATED).body(travelAgencyService.createTravelAgency(requestDto));
	}

	@DeleteMapping("/travelAgencies/{travelAgencyId}")
	@Operation(summary = "선택 이미지파일 삭제", description = "선택한 id의 이미지를 삭제합니다.")
	public ResponseEntity<ApiResponseDto> deleteTravelAgency(@PathVariable Long travelAgencyId) {
		return ResponseEntity.status(HttpStatus.OK).body(travelAgencyService.deleteTravelAgency(travelAgencyId));
	}

}
