package com.tourranger.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourranger.airline.entity.Airline;

import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long>, AirlineRepositoryCustom {
	Optional<Airline> findTopByName(String name);
}
