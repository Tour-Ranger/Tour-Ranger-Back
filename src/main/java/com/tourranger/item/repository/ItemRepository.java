package com.tourranger.item.repository;

import com.tourranger.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
	Optional<Item> findTopByOrderByIdDesc();
	Optional<Item> findByName(String name);
}