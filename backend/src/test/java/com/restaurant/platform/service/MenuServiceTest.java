package com.restaurant.platform.service;

import com.restaurant.platform.model.MenuItem;
import com.restaurant.platform.repository.MenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuService menuService;

    @Test
    void getAllMenuItems_returnsAllItems() {
        MenuItem burger = MenuItem.builder().id(1L).name("Burger").price(9.99).category("Main").available(true).build();
        MenuItem fries = MenuItem.builder().id(2L).name("Fries").price(3.99).category("Sides").available(true).build();
        when(menuRepository.findAll()).thenReturn(Arrays.asList(burger, fries));

        List<MenuItem> result = menuService.getAllMenuItems();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Burger");
        assertThat(result.get(1).getName()).isEqualTo("Fries");
    }

    @Test
    void addMenuItem_savesAndReturnsItem() {
        MenuItem input = MenuItem.builder().name("Soda").price(2.49).category("Drinks").available(true).build();
        MenuItem saved = MenuItem.builder().id(3L).name("Soda").price(2.49).category("Drinks").available(true).build();
        when(menuRepository.save(any(MenuItem.class))).thenReturn(saved);

        MenuItem result = menuService.addMenuItem(input);

        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getName()).isEqualTo("Soda");
        verify(menuRepository).save(input);
    }

    @Test
    void getMenuItemById_whenFound_returnsItem() {
        MenuItem item = MenuItem.builder().id(1L).name("Burger").price(9.99).category("Main").available(true).build();
        when(menuRepository.findById(1L)).thenReturn(Optional.of(item));

        assertThat(menuService.getMenuItemById(1L)).contains(item);
    }

    @Test
    void getMenuItemById_whenNotFound_returnsEmpty() {
        when(menuRepository.findById(99L)).thenReturn(Optional.empty());
        assertThat(menuService.getMenuItemById(99L)).isEmpty();
    }

    @Test
    void updateMenuItem_whenFound_updatesAndReturns() {
        MenuItem existing = MenuItem.builder().id(1L).name("Burger").price(9.99).category("Main").available(true).build();
        MenuItem update = MenuItem.builder().name("Cheese Burger").price(10.99).category("Main").available(false).build();
        when(menuRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(menuRepository.save(any(MenuItem.class))).thenAnswer(inv -> inv.getArgument(0));

        MenuItem result = menuService.updateMenuItem(1L, update);

        assertThat(result.getName()).isEqualTo("Cheese Burger");
        assertThat(result.getPrice()).isEqualTo(10.99);
        assertThat(result.getAvailable()).isFalse();
        verify(menuRepository).save(existing);
    }

    @Test
    void updateMenuItem_whenNotFound_throws() {
        MenuItem update = MenuItem.builder().name("X").price(1.0).category("Y").available(true).build();
        when(menuRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> menuService.updateMenuItem(99L, update))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Menu item not found: 99");
    }

    @Test
    void deleteMenuItem_callsRepository() {
        menuService.deleteMenuItem(1L);
        verify(menuRepository).deleteById(1L);
    }
}
