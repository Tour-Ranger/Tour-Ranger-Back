package com.tourranger.image.thumbnailImage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourranger.image.thumbnailImage.entity.ThumbnailImage;

@Repository
public interface ThumbnailImageRepository extends JpaRepository<ThumbnailImage, Long>, ThumbnailImageRepositoryCustom {
}