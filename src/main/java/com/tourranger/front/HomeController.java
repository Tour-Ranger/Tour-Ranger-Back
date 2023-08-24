package com.tourranger.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tourranger.item.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final ItemService itemService;

	@GetMapping("/")
	public String bannerPage() {
		return "bannerPage";
	}

	@GetMapping("/items")
	public String tourItem() {
		return "tourItemPage";
	}

}
