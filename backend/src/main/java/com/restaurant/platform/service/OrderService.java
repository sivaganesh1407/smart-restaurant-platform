package com.restaurant.platform.service;

import com.restaurant.platform.model.MenuItem;
import com.restaurant.platform.model.Order;
import com.restaurant.platform.model.OrderItem;
import com.restaurant.platform.model.dto.CreateOrderRequest;
import com.restaurant.platform.model.dto.OrderResponse;
import com.restaurant.platform.repository.MenuRepository;
import com.restaurant.platform.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }
        Order order = Order.builder()
                .orderNumber("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                .status(Order.OrderStatus.PENDING)
                .createdAt(Instant.now())
                .build();
        double total = 0;
        for (var line : request.getItems()) {
            if (line.getQuantity() == null || line.getQuantity() <= 0) continue;
            MenuItem menuItem = menuRepository.findById(line.getMenuItemId())
                    .orElseThrow(() -> new IllegalArgumentException("Menu item not found: " + line.getMenuItemId()));
            if (Boolean.FALSE.equals(menuItem.getAvailable())) {
                throw new IllegalArgumentException("Menu item not available: " + menuItem.getName());
            }
            double unitPrice = menuItem.getPrice();
            double subtotal = unitPrice * line.getQuantity();
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .menuItem(menuItem)
                    .quantity(line.getQuantity())
                    .unitPrice(unitPrice)
                    .subtotal(subtotal)
                    .build();
            order.getItems().add(orderItem);
            total += subtotal;
        }
        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one valid item");
        }
        order.setTotalAmount(total);
        order = orderRepository.save(order);
        return orderRepository.findByIdWithItems(order.getId()).map(OrderResponse::from).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAllWithItems().stream()
                .map(OrderResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findByIdWithItems(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
        return OrderResponse.from(order);
    }

    @Transactional
    public OrderResponse updateOrderStatus(Long id, Order.OrderStatus status) {
        Order order = orderRepository.findByIdWithItems(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
        order.setStatus(status);
        order = orderRepository.save(order);
        return OrderResponse.from(order);
    }
}
