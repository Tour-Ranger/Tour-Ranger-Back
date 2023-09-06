package com.tourranger.purchase.service;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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
	private final RedissonClient redissonClient;

	@Override
	@Transactional
	public boolean purchaseItem(Long itemId, PurchaseRequestDto requestDto) {
		User user = userService.findUser(requestDto.getEmail());
		Item item = itemService.findItem(itemId);

		String lockKey = "item_lock_" + itemId;
		RLock rLock = redissonClient.getLock(lockKey);

		try {
			boolean available = rLock.tryLock(5, 3, TimeUnit.SECONDS);
			if (!available) {
				return false;
			}
			checkStock(item);
			Purchase purchase = Purchase.builder().item(item).user(user).build();
			purchaseRepository.save(purchase);
			item.sellOne();

		} catch (InterruptedException e) {
			e.printStackTrace();

		} finally {
			rLock.unlock(); // (4)
		}

		return false;
	}

	private void checkStock(Item item) {
		if (item.getCurrentQuantity() == 0) {
			throw new CustomException(CustomErrorCode.OUT_OF_STOCK, null);
		}
	}
}
