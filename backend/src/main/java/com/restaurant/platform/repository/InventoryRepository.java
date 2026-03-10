package com.restaurant.platform.repository;

import com.restaurant.platform.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {

    List<InventoryItem> findByQuantityLessThanEqualOrderByQuantityAsc(Double threshold);
}
