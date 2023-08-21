package com.tourranger.item.entity;

import com.tourranger.itemImage.entity.ItemImage;
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

	@OneToMany(mappedBy = "item", orphanRemoval = true)
	private List<ItemImage> itemImageList = new ArrayList<>();

	@OneToMany(mappedBy = "item", orphanRemoval = true)
	private List<Purchase> purchaseList = new ArrayList<>();
}
