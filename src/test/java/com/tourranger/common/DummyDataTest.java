package com.tourranger.common;

import com.tourranger.common.dummy.DummyGenerator;
import com.tourranger.item.entity.Item;
import com.tourranger.item.repository.ItemRepository;
import com.tourranger.user.entity.User;
import com.tourranger.user.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DummyDataTest {
	@Autowired
	private DummyGenerator dummyGenerator;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ItemRepository itemRepository;

	@Test
	@DisplayName("Dummy Generator Test")
	void dummyGeneratorTest() {
		// dummyUserGeneretor Test
		List<User> userList1 = userRepository.findAll();
		dummyGenerator.dummyUserGenerator();
		List<User> userList2 = userRepository.findAll();

		// dummyItemGenerator Test
		dummyGenerator.dummyItemGenerator();
		Optional<Item> item = itemRepository.findById(1L);
		String name = "5박 6일 유럽 여행 초특가 할인!";
		Long price = 3_000_000L; // 3백만원
		Long discountPrice = 100L; // 백원
		Long quantity = 30L; // 30개
		Long period = 5L; // 5박 6일

		// User는 정해진 개수만큼(COUNT) 잘 생성되었는가?
		assertEquals(DummyGenerator.COUNT, userList2.size() - userList1.size());

		// item은 정해준 값으로 잘 생성되었는가?
		assertEquals(name, item.get().getName());
		assertEquals(price, item.get().getPrice());
		assertEquals(discountPrice, item.get().getDiscountPrice());
		assertEquals(quantity, item.get().getQuantity());
		assertEquals(period, item.get().getPeriod());
	}
}
