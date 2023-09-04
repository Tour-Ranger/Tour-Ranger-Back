package com.tourranger.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tour-ranger")
public class HomeController {

	@GetMapping("/")
	public String bannerPage() {
		return "bannerPage";
	}

	@GetMapping("/front/items/{itemId}")
	public String tourItem() {
		return "tourItemPage";
	}
}
