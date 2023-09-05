package com.tourranger.purchase.service;

import com.tourranger.common.exception.CustomException;
import com.tourranger.item.entity.Item;
import com.tourranger.item.service.ItemServiceImpl;
import com.tourranger.purchase.dto.PurchaseRequestDto;
import com.tourranger.purchase.entity.Purchase;
import com.tourranger.purchase.repository.PurchaseRepository;
import com.tourranger.user.entity.User;
import com.tourranger.user.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // @Mock 사용을 위해 설정
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 전역변수 사용할 때
public class PurchaseServiceTest {
	@Mock
	UserServiceImpl userService;
	@Mock
	ItemServiceImpl itemService;
	@Mock
	PurchaseRepository purchaseRepository;

	private PurchaseServiceImpl purchaseService = null;

	@BeforeEach
	@DisplayName("PurchaseServiceImpl 초기화")
	void setup() {
		purchaseService =
				new PurchaseServiceImpl(userService, itemService, purchaseRepository);
	}

	@Test
	@DisplayName("상품 구매하기 - 재고가 있는 상품")
	void purchaseItemTest() {
		// given
		Long itemId = 100L;
		String userEmail = "test-user@tour-ranger.com";
		PurchaseRequestDto requestDto = PurchaseRequestDto.builder()
				.email(userEmail).build();

		User user = new User();
		Item item = Item.builder().currentQuantity(15L).build();


		given(userService.findUser(requestDto.getEmail())).willReturn(user);
		given(itemService.findItem(itemId)).willReturn(item);

		// when
		purchaseService.purchaseItem(itemId, requestDto);

		// then
		// purchaseRepository.save() 호출되었는지 확인
		verify(purchaseRepository, times(1)).save(any(Purchase.class));
		// item currentQuantity가 1개 감소가 잘 되었는지 확인
		assertEquals(item.getCurrentQuantity(), 14);
	}

	@Test
	@DisplayName("상품 구매하기 - 재고가 없는 상품")
	void purchaseItemTest2() {
		// given
		Long itemId = 100L;
		String userEmail = "test-user@tour-ranger.com";
		PurchaseRequestDto requestDto = PurchaseRequestDto.builder()
				.email(userEmail).build();

		User user = new User();
		Item item = Item.builder().currentQuantity(0L).build();


		given(userService.findUser(requestDto.getEmail())).willReturn(user);
		given(itemService.findItem(itemId)).willReturn(item);

		// when
		Exception exception = assertThrows(CustomException.class, () -> {
			purchaseService.purchaseItem(itemId, requestDto);
		});

		// then
		assertEquals(
				"소진된 상품입니다.",
				exception.getMessage()
		);

	}

}
