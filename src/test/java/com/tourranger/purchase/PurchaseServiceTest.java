package com.tourranger.purchase;

import com.tourranger.item.entity.Item;
import com.tourranger.purchase.service.PurchaseService;
import com.tourranger.user.entity.User;
import com.tourranger.user.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PurchaseServiceTest {
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private UserRepository userRepository;

	User user = null;
	Item item = null;

	@Test
	@Order(3)
	@DisplayName("2-1. User가 없을 때")
	void findUser1() {

	}

	@Test
	@Order(4)
	@DisplayName("2-2. User가 있을 때")
	void findUser2() {

	}

	@Test
	@Order(5)
	@DisplayName("3-1. Item이 없을 때")
	void findItem1() {

	}

	@Test
	@Order(6)
	@DisplayName("3-2. Item이 있을 때")
	void findItem2() {

	}

	@Test
	@Order(7)
	@DisplayName("Item 재고가 없을 때")
	void outOfStockTest() {

	}

	@Test
	@Order(8)
	@DisplayName("Item 재고가 있을 때")
	void purchaseTest() {

	}
}
