package com.tourranger.item.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tourranger.airline.entity.Airline;
import com.tourranger.thumbnailImage.entity.ThumbnailImage;
import com.tourranger.travelAgency.entity.TravelAgency;
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

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String country;

	@Column(nullable = false)
	private Long price;

	@Column(nullable = false)
	private Long discountPrice;

	@Column(nullable = false)
	private Long currentQuantity;

	@Builder.Default
	private Long maxQuantity = 100L;

	@Column(nullable = false)
	private Long night;

	@Column(nullable = false)
	private Long day;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime departureTime;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime arrivalTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "travelAgencyId", nullable = false)
	private TravelAgency travelAgency;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "airlineId", nullable = false)
	private Airline airline;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "thumbnailImageId", nullable = false)
	private ThumbnailImage thumbnailImage;

	public void sellOne() {
		this.currentQuantity--;
	}
}
