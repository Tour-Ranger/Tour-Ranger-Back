package com.tourranger.image.repository.thumbnailImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourranger.image.entity.ThumbnailImage;

@Repository
public interface ThumbnailImageRepository extends JpaRepository<ThumbnailImage, Long>, ThumbnailImageRepositoryCustom {
}
