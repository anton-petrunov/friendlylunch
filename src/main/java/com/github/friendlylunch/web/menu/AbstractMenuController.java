package com.github.friendlylunch.web.menu;

import com.github.friendlylunch.model.Menu;
import com.github.friendlylunch.model.Restaurant;
import com.github.friendlylunch.repository.MenuRepository;
import com.github.friendlylunch.web.restaurant.RestaurantRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

import static com.github.friendlylunch.util.Util.*;
import static com.github.friendlylunch.util.ValidationUtil.*;

public abstract class AbstractMenuController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    RestaurantRestController restaurantController;

    public Menu create(Menu menu, int restaurantId) {
        log.info("create {} for restaurant {}", menu, restaurantId);
        Assert.notNull(menu, "menu must not be null");
        checkNew(menu);
        Restaurant restaurant = restaurantController.get(restaurantId);
        if (menu.getRestaurant() != null) {
            assureIdConsistent(menu.getRestaurant(), restaurantId);
        }
        menu.setRestaurant(restaurant);
        return menuRepository.save(menu);
    }

    public void update(Menu menu, int restaurantId, int id) {
        log.info("update {} of restaurant {} with id {}", menu, restaurantId, id);
        Assert.notNull(menu, "menu must not be null");
        Menu updated = get(restaurantId, id);
        assureIdConsistent(menu, id);
        Restaurant restaurant = updated.getRestaurant();
        if (menu.getRestaurant() != null) {
            assureIdConsistent(menu.getRestaurant(), restaurantId);
        }
        menu.setRestaurant(restaurant);
        menuRepository.save(menu);
    }

    public void delete(int restaurantId, int id) {
        log.info("delete menu {} of restaurant {}", id, restaurantId);
        Restaurant restaurant = restaurantController.get(restaurantId);
        checkNotFoundWithId(menuRepository.delete(restaurant.getId(), id) != 0, id);
    }

    public Menu get(int restaurantId, int id) {
        log.info("get menu {} of restaurant {}", id, restaurantId);
        Restaurant restaurant = restaurantController.get(restaurantId);
        return checkNotFoundWithId(menuRepository.get(restaurant.getId(), id), id);
    }

    public List<Menu> getAll(int restaurantId) {
        log.info("getAll menus of restaurant {}", restaurantId);
        Restaurant restaurant = restaurantController.get(restaurantId);
        return checkNotFoundWithId(menuRepository.getAll(restaurant.getId()), restaurantId);
    }

    public Menu getWithDishes(int restaurantId, int id) {
        log.info("get menu {} of restaurant {} with dishes", id, restaurantId);
        Restaurant restaurant = restaurantController.get(restaurantId);
        return checkNotFoundWithId(menuRepository.getWithDishes(restaurant.getId(), id), id);
    }

    public List<Menu> getAllCheckedWithDishes(int restaurantId) {
        log.info("getAllChecked menus of restaurant {} with dishes", restaurantId);
        Restaurant restaurant = restaurantController.get(restaurantId);
        List<Menu> menus = menuRepository.getAllByMenuDateAndDishesSizeWithDishes(restaurant.getId(), nextLunchDate);
        checkMenusListSize(menus, restaurantId);
        return checkNotFoundWithId(menus, restaurantId);
    }

    public List<Menu> getAllChecked(int restaurantId) {
        log.info("getAllChecked menus of restaurant {}", restaurantId);
        Restaurant restaurant = restaurantController.get(restaurantId);
        List<Menu> menus = menuRepository.getAllByMenuDateAndDishesSize(restaurant.getId(), nextLunchDate);
        checkMenusListSize(menus, restaurantId);
        return checkNotFoundWithId(menus, restaurantId);
    }

    public Menu getChecked(int restaurantId, int id) {
        log.info("getChecked menu {} of restaurant {}", id, restaurantId);
        Restaurant restaurant = restaurantController.get(restaurantId);
        checkNotFoundWithId(menuRepository.get(restaurant.getId(), id), id);
        Menu menu = menuRepository.getByMenuDateAndDishesSize(restaurantId, id, nextLunchDate);
        checkMenuIsNull(menu, restaurantId, id);
        return menu;
    }
}
