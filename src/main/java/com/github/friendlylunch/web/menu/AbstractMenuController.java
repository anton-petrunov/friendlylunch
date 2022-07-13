package com.github.friendlylunch.web.menu;

import com.github.friendlylunch.model.Menu;
import com.github.friendlylunch.model.Restaurant;
import com.github.friendlylunch.repository.RestaurantRepository;
import com.github.friendlylunch.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.friendlylunch.util.ValidationUtil.*;

public abstract class AbstractMenuController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    MenuService menuService;

    @Autowired
    RestaurantRepository restaurantRepository;

    public Menu create(Menu menu, int restaurantId) {
        log.info("create {} for restaurant {}", menu, restaurantId);
        checkNew(menu);
        menu.setRestaurant(restaurantRepository.getById(restaurantId));
        return menuService.create(menu);
    }

    public void update(Menu menu, int restaurantId, int id) {
        log.info("update {} of restaurant {} with id {}", menu, restaurantId, id);
        assureIdConsistent(menu, id);
        Restaurant restaurant = menuService.get(restaurantId, id).getRestaurant();
        menu.setRestaurant(restaurant);
        menuService.update(menu);
    }

    public void delete(int restaurantId, int id) {
        log.info("delete menu {} of restaurant {}", id, restaurantId);
        menuService.delete(restaurantId, id);
    }

    public Menu get(int restaurantId, int id) {
        log.info("get menu {} of restaurant {}", id, restaurantId);
        return checkNotFoundWithId(menuService.get(restaurantId, id), id);
    }

    public List<Menu> getAll(int restaurantId) {
        log.info("getAll menus of restaurant {}", restaurantId);
        return menuService.getAll(restaurantId);
    }

    public List<Menu> getAllCheckedWithDishes(int restaurantId) {
        log.info("getAllChecked menus of restaurant {} with dishes", restaurantId);
        return menuService.getAllCheckedWithDishes(restaurantId);
    }

    public List<Menu> getAllChecked(int restaurantId) {
        log.info("getAllChecked menus of restaurant {}", restaurantId);
        return menuService.getAllChecked(restaurantId);
    }

    public Menu getChecked(int restaurantId, int id) {
        log.info("getChecked menu {} of restaurant {}", id, restaurantId);
        return checkNotFoundWithId(menuService.getChecked(restaurantId, id), id);
    }

    public Menu getWithDishes(int restaurantId, int id) {
        log.info("get menu {} of restaurant {} with dishes", id, restaurantId);
        return checkNotFoundWithId(menuService.getWithDishes(restaurantId, id), id);
    }
}
