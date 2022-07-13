package com.github.friendlylunch.web.dish;

import com.github.friendlylunch.model.Dish;
import com.github.friendlylunch.repository.DishRepository;
import com.github.friendlylunch.repository.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.friendlylunch.util.Util.nextLunchDate;
import static com.github.friendlylunch.util.ValidationUtil.*;

public abstract class AbstractDishController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private MenuRepository menuRepository;

    public Dish create(Dish dish, int restaurantId, int menuId) {
        log.info("create {} menu {} for restaurant {}", dish, menuId, restaurantId);
        checkNew(dish);
        dish.setMenu(menuRepository.get(restaurantId, menuId));
        return dishRepository.save(dish);
    }

    public void update(Dish dish, int restaurantId, int menuId, int id) {
        log.info("update {} of menu {} of restaurant {} with id {}", dish, menuId, restaurantId, id);
        assureIdConsistent(dish, id);
        Dish dishFromPath = dishRepository.get(restaurantId, menuId, id);
        dish.setMenu(dishFromPath.getMenu());
        dish.getMenu().setRestaurant(dishFromPath.getMenu().getRestaurant());
        dishRepository.save(dish);
    }

    public void delete(int menuId, int id) {
        log.info("delete dish {}", id);
        checkNotFoundWithId(dishRepository.delete(menuId, id), id);
    }

    public Dish get(int restaurantId, int menuId, int id) {
        log.info("get dish {} of menu {} of restaurant {}", id, menuId, restaurantId);
        return checkNotFoundWithId(dishRepository.get(restaurantId, menuId, id), id);
    }

    public List<Dish> getAll(int restaurantId, int menuId) {
        log.info("getAll dishes of menu {} of restaurant {}", menuId, restaurantId);
        return dishRepository.getAll(restaurantId, menuId);
    }

    public List<Dish> getAllChecked(int restaurantId, int menuId) {
        log.info("getAllChecked dishes of menu {} of restaurant {}", menuId, restaurantId);
        return dishRepository.getAllCheckedByMenuDateAndDishesSize(restaurantId, menuId, nextLunchDate);
    }

    public Dish getChecked(int restaurantId, int menuId, int id) {
        log.info("getChecked dish {} of menu {} of restaurant {}", id, menuId, restaurantId);
        return checkNotFoundWithId(
                dishRepository.getCheckedByMenuDateAndDishesSize(restaurantId, menuId, id, nextLunchDate), id);
    }
}
