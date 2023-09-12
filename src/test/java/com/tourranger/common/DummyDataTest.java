package com.tourranger.common;

import com.tourranger.airline.entity.Airline;
import com.tourranger.airline.repository.AirlineRepository;
import com.tourranger.common.dummy.DummyGenerator;
import com.tourranger.item.entity.Item;
import com.tourranger.item.repository.ItemRepository;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DummyDataTest {
	@Autowired
	private DummyGenerator dummyGenerator;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private TravelAgencyRepository travelAgencyRepository;
	@Autowired
	private AirlineRepository airlineRepository;
	@Autowired
	private ThumbnailImageRepository thumbnailImageRepository;

	@Test
	@Order(1)
	@DisplayName("Dummy User Test")
	void dummyUserTest() {
		// dummyUserGeneretor Test
		List<User> userList1 = userRepository.findAll();
		dummyGenerator.dummyUserGenerator();
		List<User> userList2 = userRepository.findAll();

		// User는 정해진 개수만큼(COUNT) 잘 생성되었는가?
		assertEquals(DummyGenerator.COUNT, userList2.size() - userList1.size());
	}

	@Test
	@Order(2)
	@DisplayName("Dummy Image Test")
	void dummyImageTest() {
		dummyGenerator.dummyImageGenerator();

		TravelAgency travelAgency = travelAgencyRepository.findTopByName("투어레인저 여행사").orElse(null);
		Airline airline = airlineRepository.findTopByName("투어레인저 항공사").orElse(null);
		ThumbnailImage thumbnailImage = thumbnailImageRepository.findTopByUrl("thumbnail.jpg").orElse(null);

		assertNotNull(travelAgency);
		assertNotNull(airline);
		assertNotNull(thumbnailImage);
		assertEquals("투어레인저 여행사", travelAgency.getName());
		assertEquals("투어레인저 항공사", airline.getName());
		assertEquals("thumbnail.jpg", thumbnailImage.getUrl());
	}

	@Test
	@Order(3)
	@DisplayName("Dummy Item Test")
	@Transactional
	void dummyItemTest() {

		// dummyItemGenerator Test
		dummyGenerator.dummyItemGenerator();

		String name = "★패밀리팩 성인2+소아2 필수★괌 에어텔 / 3박4일 / 괌플라자 스탠다드 1박  + 두짓비치 슈페리어 2박 / 투어레인저";
		Long price = 529_000L; // 3백만원
		Long discountPrice = 100L; // 백원
		Long currentQuantity = 30L; // 30개
		String period = "3박 4일";
		LocalDateTime departureTime = LocalDateTime.of(2023, 9, 17, 8, 30, 0);
		LocalDateTime arrivalTime = LocalDateTime.of(2023, 9, 20, 18, 45, 0);

		String travelAgencyName = "투어레인저 여행사";
		String airlineName = "투어레인저 항공사";
		String thumbnailImageUrl = "thumbnail.jpg";

		// 마지막에 생성된 Item 찾기
		Item targetItem = itemRepository.findTopByOrderByIdDesc().orElse(null);

		// item은 정해준 값으로 잘 생성되었는가?
		assertEquals(name, targetItem.getName());
		assertEquals(price, targetItem.getPrice());
		assertEquals(discountPrice, targetItem.getDiscountPrice());
		assertEquals(currentQuantity, targetItem.getCurrentQuantity());
		assertEquals(period, targetItem.getPeriod());
		assertEquals(departureTime, targetItem.getDepartureTime());
		assertEquals(arrivalTime, targetItem.getArrivalTime());
		assertEquals(travelAgencyName, targetItem.getTravelAgency().getName());
		assertEquals(airlineName, targetItem.getAirline().getName());
		assertEquals(thumbnailImageUrl, targetItem.getThumbnailImage().getUrl());
	}
}
