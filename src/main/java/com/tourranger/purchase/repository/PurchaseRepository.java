package com.tourranger.purchase.repository;

import com.tourranger.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>, PurchaseRepositoryCustom {
	// 테스트 코드를 위한 메서드
	Optional<Purchase> findTopByOrderByIdDesc();
}
