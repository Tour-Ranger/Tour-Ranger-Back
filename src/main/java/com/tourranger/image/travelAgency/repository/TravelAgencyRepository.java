package com.tourranger.image.travelAgency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourranger.image.travelAgency.entity.TravelAgency;

@Repository
public interface TravelAgencyRepository extends JpaRepository<TravelAgency, Long>, TravelAgencyRepositoryCustom {
}