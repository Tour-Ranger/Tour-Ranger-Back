package com.tourranger.item.repository;

import com.tourranger.item.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
	Optional<Item> findTopByOrderByIdDesc();

	Page<Item> findAllByOrderById(Pageable pageable);

	//이름검색(조건-최신순)
	Page<Item> findByNameContainingOrderByIdDesc(String search, Pageable pageable);

	//이름검색(조건-가격 낮은순)
	Page<Item> findByNameContainingOrderByDiscountPrice(String search, Pageable pageable);

	//이름검색(조건-가격 높은순)
	Page<Item> findByNameContainingOrderByDiscountPriceDesc(String search, Pageable pageable);
}
