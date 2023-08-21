package com.tourranger.image.entity;

import com.tourranger.itemImage.entity.ItemImage;
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
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String url;

	@Builder.Default
	@OneToMany(mappedBy = "image", orphanRemoval = true)
	private List<ItemImage> itemImageList = new ArrayList<>();

	public Image(String url){
		this.url = url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}


