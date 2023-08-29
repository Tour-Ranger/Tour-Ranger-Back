package com.tourranger.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourranger.airline.entity.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long>, AirlineRepositoryCustom {
}
