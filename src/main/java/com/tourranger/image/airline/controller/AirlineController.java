package com.tourranger.image.airline.controller;

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
import com.tourranger.image.airline.dto.AirlineRequestDto;
import com.tourranger.image.airline.dto.AirlineResponseDto;
import com.tourranger.image.airline.service.AirlineService;

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

	@PutMapping("/airlines/{airlineId}")
	@Operation(summary = "항공사 이미지파일 변경", description = "항공사 이미지를 변경합니다.")
	public ResponseEntity<AirlineResponseDto> updateAirline( @PathVariable Long airlineId,
		@ModelAttribute AirlineRequestDto requestDto) throws IOException {
		return ResponseEntity.status(HttpStatus.OK).body(airlineService.updateAirline(airlineId, requestDto));
	}

	@PostMapping(value = "/airlines")
	@Operation(summary = "항공사 이미지파일 업로드", description = "Airline이미지를 저장합니다.")
	public ResponseEntity<AirlineResponseDto> createAirline(
		@ModelAttribute AirlineRequestDto requestDto) throws IOException {
		return ResponseEntity.status(HttpStatus.CREATED).body(airlineService.createAirline(requestDto));
	}
	@DeleteMapping("/airlines/{airlineId}")
	@Operation(summary = "선택 이미지파일 삭제", description = "선택한 id의 이미지를 삭제합니다.")
	public ResponseEntity<ApiResponseDto> deleteAirline(@PathVariable Long airlineId) {
		return ResponseEntity.status(HttpStatus.OK).body(airlineService.deleteAirline(airlineId));
	}

}
