package com.restaurant.platform.service;

import com.restaurant.platform.model.Order;
import com.restaurant.platform.repository.OrderItemRepository;
import com.restaurant.platform.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public Map<String, Object> getSalesSummary(Instant from, Instant to) {
        if (from == null) from = Instant.now().minus(30, ChronoUnit.DAYS);
        if (to == null) to = Instant.now();
        List<Order> orders = orderRepository.findByCreatedAtBetweenOrderByCreatedAtDesc(from, to);
        long completedCount = orders.stream().filter(o -> o.getStatus() == Order.OrderStatus.COMPLETED).count();
        double totalRevenue = orders.stream()
                .filter(o -> o.getStatus() == Order.OrderStatus.COMPLETED)
                .mapToDouble(o -> o.getTotalAmount() != null ? o.getTotalAmount() : 0)
                .sum();
        Map<String, Object> summary = new HashMap<>();
        summary.put("from", from.toString());
        summary.put("to", to.toString());
        summary.put("totalOrders", orders.size());
        summary.put("completedOrders", completedCount);
        summary.put("totalRevenue", totalRevenue);
        return summary;
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> getTopSellingItems(Instant from, Instant to, int limit) {
        if (from == null) from = Instant.now().minus(30, ChronoUnit.DAYS);
        if (to == null) to = Instant.now();
        if (limit <= 0) limit = 10;
        List<Object[]> rows = orderItemRepository.findTopSellingItems(from, to);
        return rows.stream()
                .limit(limit)
                .map(row -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("menuItemId", row[0]);
                    map.put("menuItemName", row[1]);
                    map.put("quantitySold", row[2]);
                    return map;
                })
                .collect(Collectors.toList());
    }
}
