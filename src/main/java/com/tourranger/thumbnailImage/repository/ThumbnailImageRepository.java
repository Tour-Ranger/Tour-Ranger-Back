package com.tourranger.thumbnailImage.repository;

import com.tourranger.thumbnailImage.entity.ThumbnailImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThumbnailImageRepository extends JpaRepository<ThumbnailImage, Long>, ThumbnailImageRepositoryCustom {
	Optional<ThumbnailImage> findTopByUrl(String url);

}
