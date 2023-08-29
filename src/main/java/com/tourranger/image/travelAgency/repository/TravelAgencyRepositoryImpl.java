package com.tourranger.image.travelAgency.repository;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TravelAgencyRepositoryImpl implements TravelAgencyRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

}
