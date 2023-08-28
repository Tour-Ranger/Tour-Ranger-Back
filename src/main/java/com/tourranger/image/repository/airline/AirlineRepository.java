package com.tourranger.image.repository.airline;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tourranger.image.entity.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long>, AirlineRepositoryCustom {
}
