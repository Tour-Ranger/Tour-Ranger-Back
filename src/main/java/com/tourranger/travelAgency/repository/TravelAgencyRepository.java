package com.tourranger.travelAgency.repository;

import com.tourranger.travelAgency.entity.TravelAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelAgencyRepository extends JpaRepository<TravelAgency, Long>, TravelAgencyRepositoryCustom {
	Optional<TravelAgency> findTopByName(String name);
}
