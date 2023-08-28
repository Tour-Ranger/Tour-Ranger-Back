package com.tourranger.image.repository.travelAgency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourranger.image.entity.TravelAgency;

@Repository
public interface TravelAgencyRepository extends JpaRepository<TravelAgency, Long>, TravelAgencyRepositoryCustom {
}
