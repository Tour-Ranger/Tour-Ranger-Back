package com.tourranger.image.airline.repository;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AirlineRepositoryImpl implements AirlineRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;
}