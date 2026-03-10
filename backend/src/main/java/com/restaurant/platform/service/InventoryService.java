package com.restaurant.platform.service;

import com.restaurant.platform.model.InventoryItem;
import com.restaurant.platform.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
    public InventoryItem addInventoryItem(InventoryItem item) {
        if (item.getLastUpdated() == null) {
            item.setLastUpdated(Instant.now());
        }
        return inventoryRepository.save(item);
    }

    @Transactional(readOnly = true)
    public List<InventoryItem> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public InventoryItem getInventoryItemById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventory item not found: " + id));
    }

    @Transactional
    public InventoryItem updateInventoryItem(Long id, Double quantity) {
        InventoryItem item = getInventoryItemById(id);
        item.setQuantity(quantity != null ? quantity : item.getQuantity());
        item.setLastUpdated(Instant.now());
        return inventoryRepository.save(item);
    }

    @Transactional
    public void deleteInventoryItem(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<InventoryItem> getLowStockItems() {
        return inventoryRepository.findAll().stream()
                .filter(i -> i.getReorderThreshold() != null && i.getQuantity() <= i.getReorderThreshold())
                .collect(java.util.stream.Collectors.toList());
    }
}
