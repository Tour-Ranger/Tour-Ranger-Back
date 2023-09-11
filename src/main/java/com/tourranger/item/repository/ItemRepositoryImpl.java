package com.tourranger.item.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tourranger.item.entity.Item;
import com.tourranger.item.entity.QItem;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom {
	private final JPAQueryFactory queryFactory;
	QItem item = QItem.item;
	@Override
	public Page<Item> findAllByOrderById(Pageable pageable){
		Long startId = pageable.getOffset()+ 1;
		Long endId = startId+4;
		JPAQuery<Item> query = queryFactory.selectFrom(item)
			.where(
				item.id.between(startId, endId)
			)
			.orderBy(item.id.asc());

		List<Item> itemList = query.fetch();

		long totalCount = query.fetchCount();//결과의 총 갯수

		return new PageImpl<>(itemList, pageable, totalCount);
	}

}


