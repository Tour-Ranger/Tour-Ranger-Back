package com.tourranger.image.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourranger.image.entity.Image;
import com.tourranger.item.entity.Item;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long>, ImageRepositoryCustom {
	List<Image> findAllByItem(Item item);
}
