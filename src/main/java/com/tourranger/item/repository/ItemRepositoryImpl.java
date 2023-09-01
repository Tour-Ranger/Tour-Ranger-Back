package com.tourranger.item.repository;

import java.util.Optional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tourranger.item.entity.Item;
import com.tourranger.item.entity.QItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<Item> findByName(String name) {
		QItem item = QItem.item;
		return Optional.ofNullable(queryFactory
			.selectFrom(item)
			.where(item.name.eq(name))
			.fetchOne());
	}
}
