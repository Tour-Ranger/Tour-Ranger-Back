package com.tourranger.purchase.controller;

import com.tourranger.purchase.service.PurchaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tour-ranger")
@Tag(name = "Purchase API", description = "Purchase에 관련된 API 정보를 담고 있습니다.")
public class PurchaseController {
	private final PurchaseService purchaseService;
}
