package com.tourranger.common.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStore {

	@Value("${upload.path}")
	private String uploadPath;

	//파일 저장 후 저장된 파일경로+파일명을 반환하는 메서드
	public String saveFile(MultipartFile multipartFile) throws IOException {
		//이미지 파일만 업로드 가능
		if (multipartFile.getContentType().startsWith("image") == false) {
			//이미지가 아닌 경우, Exception 처리
			throw new IllegalArgumentException("이미지 파일만 업로드할 수 있습니다.");
		}

		//IE나 Edge는 전체 경로가 들어오므로, 실제 파일 이름을 불러오는 메서드 사용
		String originalFileName = multipartFile.getOriginalFilename();
		//동일 파일명을 피하기 위해 랜덤값 사용하여 저장
		String saveFileName = createSaveFileName(originalFileName);
		//서버에 파일 저장
		multipartFile.transferTo(new File(getFullPath(saveFileName)));
		return saveFileName;
	}

	//파일 삭제 메서드
	public void deleteFile(String fileName) {
		File file = new File(getFullPath(fileName));

		if (file.exists()) {
			file.delete();
		}
	}

	// 파일 저장 이름 만들기
	// - 사용자들이 올리는 파일 이름이 같을 수 있으므로, 자체적으로 랜덤 이름을 만들어 사용한다
	private String createSaveFileName(String originalFilename) {
		String ext = extractExt(originalFilename);
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}

	// 확장자명 구하기
	private String extractExt(String originalFilename) {
		int pos = originalFilename.lastIndexOf(".");
		return originalFilename.substring(pos + 1);
	}

	// fullPath 만들기
	public String getFullPath(String filename) {
		String fullPath = uploadPath + filename;
		return fullPath;
	}

}

