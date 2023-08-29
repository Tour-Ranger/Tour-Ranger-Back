package com.tourranger.item.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tourranger.airline.Airline;
import com.tourranger.thumbnailImage.ThumbnailImage;
import com.tourranger.travelAgency.TravelAgency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private Long price;
	private Long discountPrice;
	private Long currentQuantity;
	private Long maxQuantity = 30L;
	private String period;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime departureTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime arrivalTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="travelAgencyId")
	private TravelAgency travelAgency;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="airlineId")
	private Airline airline;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "thumbnailId")
	private ThumbnailImage thumbnailImage;

	public void sellOne() {
		this.currentQuantity--;
	}
}
