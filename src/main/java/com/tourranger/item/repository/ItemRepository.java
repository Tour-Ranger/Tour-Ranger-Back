package com.tourranger.item.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tourranger.item.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
	Optional<Item> findTopByOrderByIdDesc();

	//이름검색(조건-최신순)
	@Query(value = "SELECT * FROM item i WHERE MATCH(i.name) AGAINST(:search IN BOOLEAN MODE) ORDER BY i.id DESC", nativeQuery = true)
	Page<Item> searchItemOrderbyIdDesc(String search, Pageable pageable);

	//이름검색(조건-가격 낮은순)
	@Query(value = "SELECT * FROM item i WHERE MATCH(i.name) AGAINST(:search IN BOOLEAN MODE) ORDER BY i.discount_price", nativeQuery = true)
	Page<Item> searchItemOrderbyDiscountPrice(String search, Pageable pageable);

	//이름검색(조건-가격 높은순)
	@Query(value = "SELECT * FROM item i WHERE MATCH(i.name) AGAINST(:search IN BOOLEAN MODE) ORDER BY i.discount_price DESC", nativeQuery = true)
	Page<Item> searchItemOrderbyDiscountPriceDesc(String search, Pageable pageable);
}

