package com.tourranger.image.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourranger.image.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long>, ImageRepositoryCustom {
	Optional<Image> findById(Long Id);
}
