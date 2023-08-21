package com.tourranger.purchase.service;

import com.tourranger.item.repository.ItemRepository;
import com.tourranger.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService{
	private final UserRepository userRepository;
	private final ItemRepository itemRepository;
}
