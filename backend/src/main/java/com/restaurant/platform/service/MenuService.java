package com.restaurant.platform.service;

import com.restaurant.platform.model.MenuItem;
import com.restaurant.platform.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuItem addMenuItem(MenuItem menuItem) {
        return menuRepository.save(menuItem);
    }

    public List<MenuItem> getAllMenuItems() {
        return menuRepository.findAll();
    }

    public Optional<MenuItem> getMenuItemById(Long id) {
        return menuRepository.findById(id);
    }

    public MenuItem updateMenuItem(Long id, MenuItem menuItem) {
        MenuItem existing = menuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Menu item not found: " + id));
        existing.setName(menuItem.getName());
        existing.setPrice(menuItem.getPrice());
        existing.setCategory(menuItem.getCategory());
        existing.setAvailable(menuItem.getAvailable());
        return menuRepository.save(existing);
    }

    public void deleteMenuItem(Long id) {
        menuRepository.deleteById(id);
    }
}
