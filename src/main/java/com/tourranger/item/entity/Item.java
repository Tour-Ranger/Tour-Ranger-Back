package com.tourranger.item.entity;

import java.util.ArrayList;
import java.util.List;

import com.tourranger.image.airline.entity.Airline;
import com.tourranger.image.thumbnailImage.entity.ThumbnailImage;
import com.tourranger.image.travelAgency.entity.TravelAgency;
import com.tourranger.purchase.entity.Purchase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
	private Long price;

	@Column(nullable = false)
	private Long discountPrice;

	@Column(nullable = false)
	private Long quantity;

	@Column(nullable = false)
	private Long period;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "travel_agency_id", nullable = false)
	private TravelAgency travelAgency;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "thumbnail_image_id", nullable = false)
	private ThumbnailImage thumbnailImage;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "airline_id", nullable = false)
	private Airline airline;

	@Builder.Default
	@OneToMany(mappedBy = "item", orphanRemoval = true)
	private List<Purchase> purchaseList = new ArrayList<>();

	public void sellOne() {
		this.quantity--;
	}
}
