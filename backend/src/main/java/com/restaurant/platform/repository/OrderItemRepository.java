package com.restaurant.platform.repository;

import com.restaurant.platform.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT oi.menuItem.id, oi.menuItem.name, SUM(oi.quantity) FROM OrderItem oi " +
           "WHERE oi.order.createdAt BETWEEN :from AND :to AND oi.order.status = 'COMPLETED' " +
           "GROUP BY oi.menuItem.id, oi.menuItem.name ORDER BY SUM(oi.quantity) DESC")
    List<Object[]> findTopSellingItems(@Param("from") Instant from, @Param("to") Instant to);
}
