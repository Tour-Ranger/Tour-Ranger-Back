package travelAgency.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelAgencyRequestDto {
	private String name;
	private MultipartFile multipartFile;
	private String url;
}
