package com.github.friendlylunch.service;

import com.github.friendlylunch.model.Menu;
import com.github.friendlylunch.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.friendlylunch.util.Util.nextLunchDate;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    public List<Menu> getAllCheckedWithDishes(int restaurantId) {
        return menuRepository.getAllCheckedByMenuDateAndDishesSizeWithDishes(restaurantId, nextLunchDate);
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

    public List<Menu> getAllChecked(int restaurantId) {
        return menuRepository.getAllCheckedByMenuDateAndDishesSize(restaurantId, nextLunchDate);
    }

    public Menu getChecked(int restaurantId, int id) {
        return menuRepository.getCheckedByMenuDateAndDishesSize(restaurantId, id, nextLunchDate);
    }

    public Menu getWithDishes(int restaurantId, int id) {
        return menuRepository.getWithDishes(restaurantId, id);
    }
}
