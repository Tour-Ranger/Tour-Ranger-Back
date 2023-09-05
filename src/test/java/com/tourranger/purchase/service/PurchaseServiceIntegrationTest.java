package com.tourranger.purchase.service;

import com.tourranger.airline.entity.Airline;
import com.tourranger.airline.repository.AirlineRepository;
import com.tourranger.item.entity.Item;
import com.tourranger.item.repository.ItemRepository;
import com.tourranger.purchase.dto.PurchaseRequestDto;
import com.tourranger.purchase.entity.Purchase;
import com.tourranger.purchase.repository.PurchaseRepository;
import com.tourranger.thumbnailImage.entity.ThumbnailImage;
import com.tourranger.thumbnailImage.repository.ThumbnailImageRepository;
import com.tourranger.travelAgency.entity.TravelAgency;
import com.tourranger.travelAgency.repository.TravelAgencyRepository;
import com.tourranger.user.entity.User;
import com.tourranger.user.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// 통합 테스트
@SpringBootTest
// 각 test가 class 취급되어 전역 필드 각 test에서 공유 가능
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// Order 순서대로 Test 진행 위해서
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PurchaseServiceIntegrationTest {
	// SpringBootTest는 Controller단만 Bean으로 주입되기 때문에 Service와 Repository단 주입 필요
	@Autowired
	PurchaseService purchaseService;
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	TravelAgencyRepository travelAgencyRepository;
	@Autowired
	AirlineRepository airlineRepository;
	@Autowired
	ThumbnailImageRepository thumbnailImageRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PurchaseRepository purchaseRepository;

	String email = "test-user@tour-ranger.com";
	Long itemId;

	@Test
	@Order(1)
	@DisplayName("구매에 필요한 User, Item 객체 생성")
	void test1() {
		TravelAgency travelAgency = TravelAgency.builder()
				.url("travelAgency.jpg")
				.name("투어레인저 여행사").build();
		Airline airline = Airline.builder()
				.url("airline.jpg")
				.name("투어레인저 항공사").build();
		ThumbnailImage thumbnailImage = ThumbnailImage.builder()
				.url("thumbnail-image.jpg").build();

		LocalDateTime departureTime = LocalDateTime.of(2023, 9, 17, 8, 30, 0);
		LocalDateTime arrivalTime = LocalDateTime.of(2023, 9, 20, 18, 45, 0);

		User user = User.builder().email(email).build();
		Item item = Item.builder().name("test-item").price(1_000_000L).discountPrice(1_000L)
				.currentQuantity(30L).period("4박 5일")
				.departureTime(departureTime).arrivalTime(arrivalTime)
				.travelAgency(travelAgency)
				.airline(airline)
				.thumbnailImage(thumbnailImage)
				.build();

		travelAgencyRepository.save(travelAgency);
		airlineRepository.save(airline);
		thumbnailImageRepository.save(thumbnailImage);
		itemRepository.save(item);
		userRepository.save(user);

		// 마지막에 생성된 Item 찾기
		Item targetItem = itemRepository.findTopByOrderByIdDesc().orElse(null);
		// 마지막에 생성된 아이템에 관한 정보 전역 필드에 저장
		itemId = targetItem.getId();

	}

	@Test
	@Order(2)
	@DisplayName("purchase item")
	@Transactional
	void test2() {
		// given
		Long itemId = this.itemId;
		PurchaseRequestDto requestDto = PurchaseRequestDto.builder().email(email).build();

		// when
		purchaseService.purchaseItem(itemId, requestDto);
		Purchase purchase = purchaseRepository.findTopByOrderByIdDesc().orElse(null);

		// then
		assertNotNull(purchase);
		assertEquals(purchase.getItem().getId(), this.itemId);
		assertEquals(purchase.getUser().getEmail(), this.email);
	}


}
