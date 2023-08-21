package com.tourranger.image.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tourranger.common.dto.ApiResponseDto;
import com.tourranger.image.Dto.ImageRequestDto;
import com.tourranger.image.Dto.ImageResponseDto;
import com.tourranger.image.service.ImageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/tour-ranger")
@Tag(name = "이미지 관련 API", description = "이미지 관련 API입니다.")
public class ImageController {
	private final ImageService imageService;

	public ImageController(ImageService imageService) {
		this.imageService = imageService;
	}

	@ResponseBody
	@GetMapping("/images/{imageId}")
	@Operation(summary = "이미지파일 조회", description = "선택한 이미지 url을 조회합니다.")
	public ResponseEntity<ImageResponseDto> getImage(
		@Parameter(name = "imageId", description = "선택한 이미지 id", in = ParameterIn.PATH) @PathVariable Long imageId
	) {
		return ResponseEntity.status(HttpStatus.OK).body(imageService.getImage(imageId));
	}

	@ResponseBody
	@PostMapping(value = "/images", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "이미지파일 업로드", description = "이미지 url을 저장합니다.")
	public ResponseEntity<ImageResponseDto> createImage(
		@Parameter(description = "이미지파일 url정보") @RequestBody ImageRequestDto requestDto
	) throws IOException {
		return ResponseEntity.status(HttpStatus.CREATED).body(imageService.createImage(requestDto.getMultipartFile()));
	}

	@ResponseBody
	@PutMapping("/images/{imageId}")
	@Operation(summary = "이미지파일 변경", description = "선택한 id의 이미지 url을 변경합니다.")
	public ResponseEntity<ImageResponseDto> updateImage(
		@Parameter(name = "imageId", description = "선택한 이미지 id", in = ParameterIn.PATH) @PathVariable Long imageId,
		@Parameter(description = "변경할 이미지파일 url정보") @RequestBody ImageRequestDto requestDto
	) {
		return ResponseEntity.status(HttpStatus.OK).body(imageService.updateImage(imageId, requestDto.getMultipartFile()));
	}

	@ResponseBody
	@DeleteMapping("/images/{imageId}")
	@Operation(summary = "이미지파일 삭제", description = "선택한 id의 이미지를 삭제합니다.")
	public ResponseEntity<ApiResponseDto> deleteImage(
		@Parameter(name = "imageId", description = "선택한 이미지 id", in = ParameterIn.PATH) @PathVariable Long imageId
	) {
		return ResponseEntity.status(HttpStatus.OK).body(imageService.deleteImage(imageId));

	}
}