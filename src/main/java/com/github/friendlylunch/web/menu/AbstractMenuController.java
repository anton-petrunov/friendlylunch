package com.github.friendlylunch.web.menu;

import com.github.friendlylunch.model.Menu;
import com.github.friendlylunch.model.Restaurant;
import com.github.friendlylunch.repository.RestaurantRepository;
import com.github.friendlylunch.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.friendlylunch.util.ValidationUtil.assureIdConsistent;
import static com.github.friendlylunch.util.ValidationUtil.checkNew;

public abstract class AbstractMenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    RestaurantRepository restaurantRepository;

    public Menu create(Menu menu, int restaurantId) {
        checkNew(menu);
        menu.setRestaurant(restaurantRepository.getById(restaurantId));
        return menuService.create(menu);
    }

    public void update(Menu menu, int restaurantId, int id) {
        assureIdConsistent(menu, id);
        Restaurant restaurant = menuService.get(restaurantId, id).getRestaurant();
        menu.setRestaurant(restaurant);
        menuService.update(menu);
    }

    public void delete(int restaurantId, int id) {
        menuService.delete(restaurantId, id);
    }

    public Menu get(int restaurantId, int id) {
        return menuService.get(restaurantId, id);
    }

    public List<Menu> getAll(int restaurantId) {
        return menuService.getAll(restaurantId);
    }

    public List<Menu> getAllCheckedWithMenus(int restaurantId) {
        return menuService.getAllCheckedWithMenus(restaurantId);
    }

    public List<Menu> getAllChecked(int restaurantId) {
        return menuService.getAllChecked(restaurantId);
    }

    public Menu getChecked(int restaurantId, int id) {
        return menuService.getChecked(restaurantId, id);
    }

    public Menu getWithDishes(int restaurantId, int id) {
        return menuService.getWithDishes(restaurantId, id);
    }
}
