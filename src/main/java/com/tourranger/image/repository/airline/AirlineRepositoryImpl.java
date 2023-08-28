package com.tourranger.image.repository.airline;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AirlineRepositoryImpl implements AirlineRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;
}