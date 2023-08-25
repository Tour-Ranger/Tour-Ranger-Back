package com.tourranger.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	@GetMapping("/")
	public String bannerPage() {
		return "bannerPage";
	}

	@GetMapping("/items/1")
	public String tourItem() {
		return "tourItemPage";
	}
}
