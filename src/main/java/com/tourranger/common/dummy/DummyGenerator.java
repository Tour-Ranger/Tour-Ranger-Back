package com.tourranger.common.dummy;

import com.github.javafaker.Faker;
import com.tourranger.item.entity.Item;
import com.tourranger.item.repository.ItemRepository;
import com.tourranger.user.entity.User;
import com.tourranger.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DummyGenerator implements CommandLineRunner {
	private final UserRepository userRepository;
	private final ItemRepository itemRepository;
	private final Faker faker;

	// 상수
	public static final int COUNT = 10;

	// Faker 주입
	@Autowired
	public DummyGenerator(UserRepository userRepository, ItemRepository itemRepository) {
		this.faker = new Faker();
		this.userRepository = userRepository;
		this.itemRepository = itemRepository;
	}

	// 실행
	@Override
	public void run(String... args) throws Exception {
//		dummyUserGenerator();
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

	// Item 만들기
	public void dummyItemGenerator() {
		String name = "5박 6일 유럽 여행 초특가 할인!";
		Long price = 3_000_000L; // 3백만원
		Long discountPrice = 100L; // 백원
		Long quantity = 30L; // 30개
		Long period = 5L; // 5박 6일
		Item item = Item.builder().name(name).price(price)
				.discountPrice(discountPrice).quantity(quantity)
				.period(period).build();
		itemRepository.save(item);
	}
}

