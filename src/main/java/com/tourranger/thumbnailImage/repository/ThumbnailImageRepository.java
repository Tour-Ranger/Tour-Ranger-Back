package com.tourranger.thumbnailImage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourranger.thumbnailImage.entity.ThumbnailImage;

@Repository
public interface ThumbnailImageRepository extends JpaRepository<ThumbnailImage, Long>, ThumbnailImageRepositoryCustom {
}
