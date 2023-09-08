package com.tourranger.common.namedLock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tourranger.item.entity.Item;

public interface LockItemRepository extends JpaRepository<Item, Long> {
	@Query(value = "select get_lock(:key,100)", nativeQuery = true)
	void getLock(String key);

	@Query(value = "select release_lock(:key)", nativeQuery = true)
	void releaseLock(String key);
}
