package com.tourranger.common.dummy;

import com.github.javafaker.Faker;
import com.tourranger.user.entity.User;
import com.tourranger.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DummyGenerator implements CommandLineRunner {
	private final UserRepository userRepository;
	private final Faker faker;

	// 상수
	public static final int COUNT = 10;

	// Faker 주입
	@Autowired
	public DummyGenerator(UserRepository userRepository) {
		this.faker = new Faker();
		this.userRepository = userRepository;
	}

	// 실행
	@Override
	public void run(String... args) throws Exception {
//		dummyUserGenerator();
	}

	// User 더미데이터 만들기
	public void dummyUserGenerator() {
		for (int i = 0; i < COUNT; i++) {
			String email = faker.internet().emailAddress();

			User user = User.builder().email(email).build();
			userRepository.save(user);
		}
	}
}

