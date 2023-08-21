package com.tourranger.image.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tourranger.image.entity.Image;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepositoryCustom{
	private final JPAQueryFactory jpaQueryFactory;



}
