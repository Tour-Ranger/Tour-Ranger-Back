package com.tourranger.common.dummy;

import com.github.javafaker.Faker;
import com.tourranger.airline.Airline;
import com.tourranger.airline.AirlineRepository;
import com.tourranger.item.entity.Item;
import com.tourranger.item.repository.ItemRepository;
import com.tourranger.thumbnailImage.ThumbnailImage;
import com.tourranger.thumbnailImage.ThumbnailImageRepository;
import com.tourranger.travelAgency.TravelAgency;
import com.tourranger.travelAgency.TravelAgencyRepository;
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
				.imageUrl("tour-ranger-airline.jpg").build();
		ThumbnailImage thumbnailImage = ThumbnailImage.builder()
				.imageUrl("thumbnail.jpg").build();
		TravelAgency travelAgency = TravelAgency.builder()
				.name("투어레인저 여행사")
				.imageUrl("tour-ranger-travel-agency.jpg").build();
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
		LocalDateTime departureTime = LocalDateTime.parse("2023-09-17 08:30:00");
		LocalDateTime arrivalTime = LocalDateTime.parse("2023-09-20 18:45:00");
		TravelAgency travelAgency = travelAgencyRepository.findById(1L).orElse(null);
		Airline airline = airlineRepository.findById(1L).orElse(null);
		ThumbnailImage thumbnailImage = thumbnailImageRepository.findById(1L).orElse(null);
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

