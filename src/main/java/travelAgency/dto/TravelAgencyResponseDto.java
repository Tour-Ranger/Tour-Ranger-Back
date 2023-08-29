package travelAgency.dto;

import lombok.Getter;
import travelAgency.entity.TravelAgency;

@Getter
public class TravelAgencyResponseDto {
	private Long id;
	private String name;
	private String url;

	public TravelAgencyResponseDto(TravelAgency travelAgency) {
		this.id = travelAgency.getId();
		this.name = travelAgency.getName();
		this.url = travelAgency.getUrl();
	}
}
