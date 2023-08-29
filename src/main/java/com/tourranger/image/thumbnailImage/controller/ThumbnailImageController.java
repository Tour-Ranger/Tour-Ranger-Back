package com.tourranger.image.thumbnailImage.controller;

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
import com.tourranger.image.thumbnailImage.dto.ThumbnailImageRequestDto;
import com.tourranger.image.thumbnailImage.dto.ThumbnailImageResponseDto;
import com.tourranger.image.thumbnailImage.service.ThumbnailImageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tour-ranger")
@RequiredArgsConstructor
@Tag(name = "썸네일 이미지 관련 API", description = "썸네일 이미지 관련 API입니다.")
public class ThumbnailImageController {
	private final ThumbnailImageService thumbnailImageService;

	@GetMapping("/thumbnailImages/{thumbnailImageId}")
	@Operation(summary = "썸네일 이미지파일 조회", description = "썸네일 이미지 url의 Resource를 조회합니다.")
	public ResponseEntity<Resource> getThumbnailImage(@PathVariable Long thumbnailImageId) throws
		MalformedURLException {
		return ResponseEntity.status(HttpStatus.OK).body(thumbnailImageService.getThumbnailImage(thumbnailImageId));
	}

	@PutMapping("/thumbnailImages/{thumbnailImageId}")
	@Operation(summary = "썸네일 이미지파일 변경", description = "썸네일 이미지를 변경합니다.")
	public ResponseEntity<ThumbnailImageResponseDto> updateThumbnailImage(@PathVariable Long thumbnailImageId,
		@ModelAttribute ThumbnailImageRequestDto requestDto) throws IOException {
		return ResponseEntity.status(HttpStatus.OK)
			.body(thumbnailImageService.updateThumbnailImage(thumbnailImageId, requestDto));
	}

	@PostMapping(value = "/thumbnailImages")
	@Operation(summary = "썸네일 이미지파일 업로드", description = "썸네일 이미지를 저장합니다.")
	public ResponseEntity<ThumbnailImageResponseDto> createThumbnailImage(
		@ModelAttribute ThumbnailImageRequestDto requestDto) throws IOException {
		return ResponseEntity.status(HttpStatus.CREATED).body(thumbnailImageService.createThumbnailImage(requestDto));
	}

	@DeleteMapping("/thumbnailImages/{thumbnailImageId}")
	@Operation(summary = "썸네일 이미지파일 삭제", description = "선택한 id의 썸네일 이미지를 삭제합니다.")
	public ResponseEntity<ApiResponseDto> deleteThumbnailImage(@PathVariable Long thumbnailImageId) {
		return ResponseEntity.status(HttpStatus.OK).body(thumbnailImageService.deleteThumbnailImage(thumbnailImageId));
	}

}
