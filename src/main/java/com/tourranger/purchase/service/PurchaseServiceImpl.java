package com.tourranger.purchase.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tourranger.common.error.CustomErrorCode;
import com.tourranger.common.exception.CustomException;
import com.tourranger.item.entity.Item;
import com.tourranger.item.service.ItemServiceImpl;
import com.tourranger.purchase.dto.PurchaseRequestDto;
import com.tourranger.purchase.entity.Purchase;
import com.tourranger.purchase.repository.PurchaseRepository;
import com.tourranger.user.entity.User;
import com.tourranger.user.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
	private final UserServiceImpl userService;
	private final ItemServiceImpl itemService;
	private final PurchaseRepository purchaseRepository;

	private static final Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);

	@Override
	@Transactional
	@Async("taskExecutor")
	public void purchaseItem(Long itemId, PurchaseRequestDto requestDto) {
		// String threadName = Thread.currentThread().getName(); // 현재 스레드의 이름 가져오기
		// logger.info("start purchaseItem(), " + threadName);
		User user = userService.findUser(requestDto.getEmail());
		if (user == null) {
			throw new CustomException(CustomErrorCode.USER_NOT_FOUND, null);
		}
		Item item = itemService.findItemPessimisticLock(itemId);
		checkStock(item);
		Purchase purchase = Purchase.builder().item(item).user(user).build();
		purchaseRepository.save(purchase);
		item.sellOne();
		// logger.info("end purchaseItem(), " + threadName);
	}

	private void checkStock(Item item) {
		if (item.getCurrentQuantity() == 0) {
			throw new CustomException(CustomErrorCode.OUT_OF_STOCK, null);
		}
	}
}
