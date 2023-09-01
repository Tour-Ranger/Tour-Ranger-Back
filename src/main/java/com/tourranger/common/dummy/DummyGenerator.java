package com.tourranger.common.dummy;

import com.github.javafaker.Faker;
import com.tourranger.airline.entity.Airline;
import com.tourranger.airline.repository.AirlineRepository;
import com.tourranger.item.entity.Item;
import com.tourranger.item.repository.ItemRepository;
import com.tourranger.thumbnailImage.entity.ThumbnailImage;
import com.tourranger.thumbnailImage.repository.ThumbnailImageRepository;
import com.tourranger.travelAgency.entity.TravelAgency;
import com.tourranger.travelAgency.repository.TravelAgencyRepository;
import com.tourranger.user.entity.User;
import com.tourranger.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DummyGenerator implements CommandLineRunner {
	private final UserRepository userRepository;
	private final ItemRepository itemRepository;
	private final AirlineRepository airlineRepository;
	private final ThumbnailImageRepository thumbnailImageRepository;
	private final TravelAgencyRepository travelAgencyRepository;
	private final Faker faker;

	// 상수
	public static final int COUNT = 10;

	// Faker 주입
	@Autowired
	public DummyGenerator(UserRepository userRepository,
						  ItemRepository itemRepository,
						  AirlineRepository airlineRepository,
						  ThumbnailImageRepository thumbnailImageRepository,
						  TravelAgencyRepository travelAgencyRepository) {
		this.faker = new Faker();
		this.userRepository = userRepository;
		this.itemRepository = itemRepository;
		this.airlineRepository = airlineRepository;
		this.thumbnailImageRepository = thumbnailImageRepository;
		this.travelAgencyRepository = travelAgencyRepository;
	}

	// 실행
	@Override
	public void run(String... args) throws Exception {
//		dummyUserGenerator();
//		dummyImageGenerator();
//		dummyItemGenerator();
	}

	// User 더미데이터 만들기
	public void dummyUserGenerator() {
		for (int i = 0; i < COUNT; i++) {
			String email = faker.internet().emailAddress();

			User user = User.builder().email(email).build();
			userRepository.save(user);
		}
	}

	// Image 더미데이터 만들기
	public void dummyImageGenerator() {
		Airline airline = Airline.builder()
				.name("투어레인저 항공사")
				.url("tour-ranger-airline.jpg").build();
		ThumbnailImage thumbnailImage = ThumbnailImage.builder()
				.url("thumbnail.jpg").build();
		TravelAgency travelAgency = TravelAgency.builder()
				.name("투어레인저 여행사")
				.url("tour-ranger-travel-agency.jpg").build();
		airlineRepository.save(airline);
		thumbnailImageRepository.save(thumbnailImage);
		travelAgencyRepository.save(travelAgency);
	}

	// Item 만들기
	public void dummyItemGenerator() {
		String name = "★패밀리팩 성인2+소아2 필수★괌 에어텔 / 3박4일 / 괌플라자 스탠다드 1박  + 두짓비치 슈페리어 2박 / 투어레인저";
		Long price = 529_000L; // 3백만원
		Long discountPrice = 100L; // 백원
		Long currentQuantity = 30L; // 30개
		String period = "3박 4일";
		LocalDateTime departureTime = LocalDateTime.of(2023,9,17,8,30,0);
		LocalDateTime arrivalTime = LocalDateTime.of(2023,9,20,18,45,0);
		TravelAgency travelAgency = travelAgencyRepository.findTopByName("투어레인저 여행사").orElse(null);
		Airline airline = airlineRepository.findTopByName("투어레인저 항공사").orElse(null);
		ThumbnailImage thumbnailImage = thumbnailImageRepository.findTopByUrl("thumbnail.jpg").orElse(null);
		Item item = Item.builder()
				.name(name).price(price).discountPrice(discountPrice)
				.currentQuantity(currentQuantity).period(period)
				.departureTime(departureTime)
				.arrivalTime(arrivalTime)
				.travelAgency(travelAgency)
				.airline(airline)
				.thumbnailImage(thumbnailImage).build();
		itemRepository.save(item);
	}
}

