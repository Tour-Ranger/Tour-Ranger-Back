package com.tourranger.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tour-ranger")
public class HomeController {

	@GetMapping("/")
	public String bannerPage() {
		return "bannerPage";
	}

	@GetMapping("/front/items")
	public String tourItem() {
		return "tourItemPage";
	}
}
