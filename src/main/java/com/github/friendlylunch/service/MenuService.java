package com.github.friendlylunch.service;

import com.github.friendlylunch.model.Menu;
import com.github.friendlylunch.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.friendlylunch.util.Util.nextLunchDateTime;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    public List<Menu> getAllMenusForNextLunch() {
        return menuRepository.getAllByDateWithDishes(nextLunchDateTime().toLocalDate()).stream()
                .filter(menu -> menu.getDishes().size() > 0)
                .collect(Collectors.toList());
    }

    public Menu create(Menu menu) {
        return menuRepository.save(menu);
    }

    public void update(Menu menu) {
        menuRepository.save(menu);
    }

    public void delete(int restaurantId, int id) {
        menuRepository.delete(restaurantId, id);
    }

    public Menu get(int restaurantId, int id) {
        return menuRepository.get(restaurantId, id);
    }

    public List<Menu> getAll(int restaurantId) {
        return menuRepository.getAll(restaurantId);
    }
}
