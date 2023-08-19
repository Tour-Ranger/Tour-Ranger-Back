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
		List<Item> itemList1 = itemRepository.findAll();
		dummyGenerator.dummyItemGenerator();
		List<Item> itemList2 = itemRepository.findAll();

		assertEquals(DummyGenerator.COUNT, userList2.size() - userList1.size());
		assertEquals(1, itemList2.size() - itemList1.size());
	}
}
