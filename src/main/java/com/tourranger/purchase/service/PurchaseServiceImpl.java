package com.tourranger.purchase.service;

import com.tourranger.common.error.CustomErrorCode;
import com.tourranger.common.exception.CustomException;
import com.tourranger.item.entity.Item;
import com.tourranger.item.repository.ItemRepository;
import com.tourranger.purchase.dto.PurchaseRequestDto;
import com.tourranger.purchase.entity.Purchase;
import com.tourranger.purchase.repository.PurchaseRepository;
import com.tourranger.user.entity.User;
import com.tourranger.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
	private final UserRepository userRepository;
	private final ItemRepository itemRepository;
	private final PurchaseRepository purchaseRepository;

	@Override
	@Transactional
	public void purchaseItem(Long itemId, PurchaseRequestDto requestDto) {
		User user = findUser(requestDto.getEmail());
		Item item = findItem(itemId);
		checkStock(item);
		Purchase purchase = Purchase.builder().item(item).user(user).build();
		purchaseRepository.save(purchase);
		item.sellOne();
	}

	private User findUser(String email) {
		return userRepository.findByEmail(email).orElseThrow(() ->
				new CustomException(CustomErrorCode.USER_NOT_FOUND, null)
		);
	}

	private Item findItem(Long id) {
		return itemRepository.findById(id).orElseThrow(() ->
				new CustomException(CustomErrorCode.ITEM_NOT_FOUND, null)
		);
	}

	private void checkStock(Item item) {
		if (item.getQuantity() == 0) {
			throw new CustomException(CustomErrorCode.OUT_OF_STOCK, null);
		}
	}
}
