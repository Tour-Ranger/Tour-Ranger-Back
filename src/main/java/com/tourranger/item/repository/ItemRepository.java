package com.tourranger.item.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tourranger.item.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
	Optional<Item> findTopByOrderByIdDesc();

	//이름검색(조건-최신순)
	@Query(value = "SELECT * "
		+ "FROM item i "
		+ "WHERE MATCH(i.name) AGAINST(:search IN BOOLEAN MODE) "
		+ "AND (COALESCE(:countries) IS NULL OR country IN (:countries)) "
		+ "AND (COALESCE(:travelAgencies) IS NULL OR travel_agency_id IN (:travelAgencies)) "
		+ "AND (:startDate IS NULL OR (DATE(departure_time) = :startDate AND DATE(arrival_time) = :endDate)) "
		+ "AND (:priceValue IS NULL OR ((:priceAbove = true AND :priceValue <= discount_price) OR (:priceAbove = false AND :priceValue >= discount_price))) "
		+ "ORDER BY i.id DESC", nativeQuery = true)
	Page<Item> searchItemOrderbyIdDesc(String search, Pageable pageable, List<String> countries,
		List<Integer> travelAgencies, String startDate, String endDate, Integer priceValue, boolean priceAbove);

	//searchItem 테스트1 - 필터 미적용 검색
	@Query(value = "SELECT * FROM item i WHERE MATCH(i.name) AGAINST(:search IN BOOLEAN MODE) ORDER BY i.id DESC", nativeQuery = true)
	Page<Item> searchItemNoFilterTest(String search, Pageable pageable);

	@Query(value = "SELECT * "
		+ "FROM item i "
		+ "WHERE i.name LIKE CONCAT('%', :search, '%') "
		+ "AND (COALESCE(:countries) IS NULL OR country IN (:countries)) "
		+ "AND (COALESCE(:travelAgencies) IS NULL OR travel_agency_id IN (:travelAgencies)) "
		+ "AND (:startDate IS NULL OR (DATE(departure_time) = :startDate AND DATE(arrival_time) = :endDate)) "
		+ "AND (:priceValue IS NULL OR ((:priceAbove = true AND :priceValue <= discount_price) OR (:priceAbove = false AND :priceValue >= discount_price))) "
		+ "ORDER BY i.id DESC", nativeQuery = true)
	Page<Item> searchItemLikeTest(String search, Pageable pageable, List<String> countries,
		List<Integer> travelAgencies, String startDate, String endDate, Integer priceValue, boolean priceAbove);

	//이름검색(조건-가격 낮은순)
	@Query(value = "SELECT * "
		+ "FROM item i "
		+ "WHERE MATCH(i.name) AGAINST(:search IN BOOLEAN MODE) "
		+ "AND (COALESCE(:countries) IS NULL OR country IN (:countries)) "
		+ "AND (COALESCE(:travelAgencies) IS NULL OR travel_agency_id IN (:travelAgencies)) "
		+ "AND (:startDate IS NULL OR (DATE(departure_time) = :startDate AND DATE(arrival_time) = :endDate)) "
		+ "AND (:priceValue IS NULL OR ((:priceAbove = true AND :priceValue <= discount_price) OR (:priceAbove = false AND :priceValue >= discount_price))) "
		+ " ORDER BY i.discount_price", nativeQuery = true)
	Page<Item> searchItemOrderbyDiscountPrice(String search, Pageable pageable, List<String> countries,
		List<Integer> travelAgencies, String startDate, String endDate, Integer priceValue, boolean priceAbove);

	;

	//이름검색(조건-가격 높은순)

	@Query(value = "SELECT * "
		+ "FROM item i "
		+ "WHERE MATCH(i.name) AGAINST(:search IN BOOLEAN MODE) "
		+ "AND (COALESCE(:countries) IS NULL OR country IN (:countries)) "
		+ "AND (COALESCE(:travelAgencies) IS NULL OR travel_agency_id IN (:travelAgencies)) "
		+ "AND (:startDate IS NULL OR (DATE(departure_time) = :startDate AND DATE(arrival_time) = :endDate)) "
		+ "AND (:priceValue IS NULL OR ((:priceAbove = true AND :priceValue <= discount_price) OR (:priceAbove = false AND :priceValue >= discount_price))) "
		+ "ORDER BY i.discount_price DESC", nativeQuery = true)
	Page<Item> searchItemOrderbyDiscountPriceDesc(String search, Pageable pageable, List<String> countries,
		List<Integer> travelAgencies, String startDate, String endDate, Integer priceValue, boolean priceAbove);

	;
}


