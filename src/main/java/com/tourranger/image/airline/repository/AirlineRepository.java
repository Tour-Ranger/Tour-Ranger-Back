package com.tourranger.image.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tourranger.image.airline.entity.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long>, AirlineRepositoryCustom {
}
