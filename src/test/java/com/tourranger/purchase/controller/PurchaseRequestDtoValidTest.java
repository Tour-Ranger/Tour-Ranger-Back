package com.tourranger.purchase.controller;

import com.tourranger.purchase.dto.PurchaseRequestDto;
import com.tourranger.purchase.service.PurchaseService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@WebMvcTest(PurchaseController.class)
public class PurchaseRequestDtoValidTest {
	@Autowired
	private Validator validator;
	@MockBean
	private PurchaseService purchaseService;

	@Test
	@DisplayName("validation Test")
	void PurchaseItemValidTest() {
		// given
		String email = "1234";
		PurchaseRequestDto requestDto =
				PurchaseRequestDto.builder().email(email).build();

		// when
		Set<ConstraintViolation<PurchaseRequestDto>> validate = validator.validate(requestDto);

		// then
		Iterator<ConstraintViolation<PurchaseRequestDto>> iterator = validate.iterator();
		List<String> messages = new ArrayList<>();
		while (iterator.hasNext()) {
			ConstraintViolation<PurchaseRequestDto> next = iterator.next();
			messages.add(next.getMessage());
			System.out.println("message = " + next.getMessage());
		}

		Assertions.assertThat(messages).contains("이메일 형식이 아닙니다");
	}

}
