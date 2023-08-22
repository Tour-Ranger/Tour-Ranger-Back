package com.tourranger.image.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tourranger.common.dto.ApiResponseDto;
import com.tourranger.image.Dto.ImageResponseDto;
import com.tourranger.image.service.ImageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tour-ranger")
@RequiredArgsConstructor
@Tag(name = "이미지 관련 API", description = "이미지 관련 API입니다.")
public class ImageController {
	private final ImageService imageService;

	@GetMapping("/images/{imageId}")
	@Operation(summary = "선택 이미지파일 조회", description = "선택한 이미지 url의 Resource를 조회합니다.")
	public ResponseEntity<Resource> getImage(@PathVariable Long imageId) throws MalformedURLException {
		return ResponseEntity.status(HttpStatus.OK).body(imageService.getImage(imageId));
	}

	@GetMapping("/images")
	@Operation(summary = "전체 이미지파일 조회", description = "전체 이미지 url을 조회합니다.")
	public ResponseEntity<List<ImageResponseDto>> getImageList() {
		return ResponseEntity.status(HttpStatus.OK).body(imageService.getImageList());
	}

	@PostMapping(value = "/images")
	@Operation(summary = "이미지파일 업로드", description = "이미지 url을 저장합니다.")
	public ResponseEntity<ImageResponseDto> createImage(
		@RequestPart("multipartFile") MultipartFile multipartFile) throws IOException {
		return ResponseEntity.status(HttpStatus.CREATED).body(imageService.createImage(multipartFile));
	}

	@PutMapping("/images/{imageId}")
	@Operation(summary = "이미지파일 변경", description = "선택한 id의 이미지 url을 변경합니다.")
	public ResponseEntity<ImageResponseDto> updateImage(@PathVariable Long imageId,
		@RequestPart("multipartFile") MultipartFile multipartFile) throws IOException {
		return ResponseEntity.status(HttpStatus.OK).body(imageService.updateImage(imageId, multipartFile));
	}

	@DeleteMapping("/images/{imageId}")
	@Operation(summary = "이미지파일 삭제", description = "선택한 id의 이미지를 삭제합니다.")
	public ResponseEntity<ApiResponseDto> deleteImage(@PathVariable Long imageId) {
		return ResponseEntity.status(HttpStatus.OK).body(imageService.deleteImage(imageId));

	}
}