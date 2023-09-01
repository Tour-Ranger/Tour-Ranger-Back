package com.tourranger.item.repository;

import java.util.Optional;

import com.tourranger.item.entity.Item;

public interface ItemRepositoryCustom {
	Optional<Item> findByName(String name);
}
