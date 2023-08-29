package com.tourranger.thumbnailImage.controller;

import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourranger.thumbnailImage.service.ThumbnailImageService;

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

}
