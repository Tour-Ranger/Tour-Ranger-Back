package com.tourranger.item.entity;

import com.tourranger.image.entity.Airline;
import com.tourranger.image.entity.Image;
import com.tourranger.image.entity.ThumbnailImage;
import com.tourranger.image.entity.TravelAgency;
import com.tourranger.purchase.entity.Purchase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
	private List<Image> imageList = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "item", orphanRemoval = true)
	private List<Purchase> purchaseList = new ArrayList<>();

	public void sellOne() {
		this.quantity--;
	}
}
