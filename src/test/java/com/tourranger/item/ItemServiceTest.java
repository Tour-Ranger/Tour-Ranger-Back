package com.tourranger.item;

import com.tourranger.airline.entity.Airline;
import com.tourranger.airline.repository.AirlineRepository;
import com.tourranger.item.dto.ItemResponseDto;
import com.tourranger.item.entity.Item;
import com.tourranger.item.repository.ItemRepository;
import com.tourranger.item.service.ItemServiceImpl;
import com.tourranger.thumbnailImage.entity.ThumbnailImage;
import com.tourranger.thumbnailImage.repository.ThumbnailImageRepository;
import com.tourranger.travelAgency.entity.TravelAgency;
import com.tourranger.travelAgency.repository.TravelAgencyRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemServiceTest {
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private TravelAgencyRepository travelAgencyRepository;
	@Autowired
	private AirlineRepository airlineRepository;
	@Autowired
	private ThumbnailImageRepository thumbnailImageRepository;
	@Autowired
	private ItemServiceImpl itemService;

	Long itemId = null;
	String name = "투어레인저 패키지";
	Long price = 529_000L; // 3백만원
	Long discountPrice = 100L; // 백원
	Long currentQuantity = 30L; // 30개
	String period = "3박 4일";
	LocalDateTime departureTime = LocalDateTime.of(2023, 9, 17, 8, 30, 0);
	LocalDateTime arrivalTime = LocalDateTime.of(2023, 9, 20, 18, 45, 0);

	@Test
	@BeforeAll
	@DisplayName("필요한 Entity 저장")
	void saveEntities() {
		TravelAgency travelAgency = TravelAgency.builder()
				.url("travelAgency.jpg")
				.name("투어레인저 여행사").build();
		Airline airline = Airline.builder()
				.url("airline.jpg")
				.name("투어레인저 항공사").build();
		ThumbnailImage thumbnailImage = ThumbnailImage.builder()
				.url("thumbnail-image.jpg").build();
		Item item = Item.builder().name(name).price(price).discountPrice(discountPrice)
				.currentQuantity(currentQuantity).period(period)
				.departureTime(departureTime).arrivalTime(arrivalTime)
				.travelAgency(travelAgency)
				.airline(airline)
				.thumbnailImage(thumbnailImage)
				.build();

		travelAgencyRepository.save(travelAgency);
		airlineRepository.save(airline);
		thumbnailImageRepository.save(thumbnailImage);
		itemRepository.save(item);

		// 마지막에 생성된 Item 찾기
		Item targetItem = itemRepository.findTopByOrderByIdDesc().orElse(null);
		itemId = targetItem.getId();
	}

	@Test
	@Order(1)
	@Transactional
	@DisplayName("getItem Test")
	void getItemTest() {
		ItemResponseDto responseDto = itemService.getItem(itemId);

		// item은 정해준 값으로 잘 생성되었는가?
		assertEquals(name, responseDto.getName());
		assertEquals(price, responseDto.getPrice());
		assertEquals(discountPrice, responseDto.getDiscountPrice());
		assertEquals(currentQuantity, responseDto.getCurrentQuantity());
		assertEquals(period, responseDto.getPeriod());
		assertEquals(departureTime, responseDto.getDepartureTime());
		assertEquals(arrivalTime, responseDto.getArrivalTime());
		assertEquals("투어레인저 여행사", responseDto.getTravelAgency());
		assertEquals("투어레인저 항공사", responseDto.getAirline());
	}
}
