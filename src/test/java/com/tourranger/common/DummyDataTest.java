package com.tourranger.common;

import com.tourranger.common.dummy.DummyGenerator;
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

	@Test
	@DisplayName("Dummy Generator Test")
	void dummyGeneratorTest() {
		List<User> userList1 = userRepository.findAll();
		dummyGenerator.dummyUserGenerator();
		List<User> userList2 = userRepository.findAll();

		assertEquals(DummyGenerator.COUNT, userList2.size() - userList1.size());
	}
}
