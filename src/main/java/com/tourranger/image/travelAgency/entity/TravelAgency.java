package com.tourranger.image.travelAgency.entity;

import java.util.ArrayList;
import java.util.List;

import com.tourranger.item.entity.Item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class TravelAgency {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column(nullable = false)
	private String url;

	@Builder.Default
	@OneToMany(mappedBy = "travelAgency", orphanRemoval = true)
	private List<Item> itemList = new ArrayList<>();

	public TravelAgency(String name, String url){
		this.name = name;
		this.url = url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
