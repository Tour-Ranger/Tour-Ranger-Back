package com.tourranger.image.repository.thumbnailImage;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ThumbnailImageRepositoryImpl implements ThumbnailImageRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

}
