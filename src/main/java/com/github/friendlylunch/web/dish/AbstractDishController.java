package com.github.friendlylunch.web.dish;

import com.github.friendlylunch.model.Dish;
import com.github.friendlylunch.model.Menu;
import com.github.friendlylunch.repository.DishRepository;
import com.github.friendlylunch.web.menu.MenuRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

import static com.github.friendlylunch.util.Util.nextLunchDate;
import static com.github.friendlylunch.util.ValidationUtil.*;

public abstract class AbstractDishController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private MenuRestController menuController;

    public Dish create(Dish dish, int restaurantId, int menuId) {
        log.info("create {} of menu {} of restaurant {}", dish, menuId, restaurantId);
        Assert.notNull(dish, "dish must not be null");
        checkNew(dish);
        Menu menu = menuController.get(restaurantId, menuId);
        if (dish.getMenu() != null) {
            assureIdConsistent(dish.getMenu(), menuId);
        }
        dish.setMenu(menu);
        return dishRepository.save(dish);
    }

    public void update(Dish dish, int restaurantId, int menuId, int id) {
        log.info("update {} of menu {} of restaurant {} with id {}", dish, menuId, restaurantId, id);
        Assert.notNull(dish, "dish must not be null");
        Dish updated = get(restaurantId, menuId, id);
        assureIdConsistent(dish, id);
        Menu menu = updated.getMenu();
        if (dish.getMenu() != null) {
            assureIdConsistent(dish.getMenu(), menuId);
        }
        dish.setMenu(menu);
        checkNotFoundWithId(dishRepository.save(dish), id);
    }

    public void delete(int restaurantId, int menuId, int id) {
        log.info("delete dish {} from menu {} from restaurant {}", id, menuId, restaurantId);
        Menu menu = menuController.get(restaurantId, menuId);
        checkNotFoundWithId(dishRepository.delete(menu.getId(), id) != 0, id);
    }

    public Dish get(int restaurantId, int menuId, int id) {
        log.info("get dish {} of menu {} of restaurant {}", id, menuId, restaurantId);
        Menu menu = menuController.get(restaurantId, menuId);
        return checkNotFoundWithId(dishRepository.get(menu.getId(), id), id);
    }

    public List<Dish> getAll(int restaurantId, int menuId) {
        log.info("getAll dishes of menu {} of restaurant {}", menuId, restaurantId);
        Menu menu = menuController.get(restaurantId, menuId);
        return checkNotFoundWithId(dishRepository.getAll(menu.getId()), menuId);
    }

    public List<Dish> getAllChecked(int restaurantId, int menuId) {
        log.info("getAllChecked dishes of menu {} of restaurant {}", menuId, restaurantId);
        Menu menu = menuController.get(restaurantId, menuId);
        return checkNotFoundWithId(
                dishRepository.getAllCheckedByMenuDateAndDishesSize(menu.getId(), nextLunchDate),
                menuId);
    }

    public Dish getChecked(int restaurantId, int menuId, int id) {
        log.info("getChecked dish {} of menu {} of restaurant {}", id, menuId, restaurantId);
        Menu menu = menuController.get(restaurantId, menuId);
        return checkNotFoundWithId(
                dishRepository.getCheckedByMenuDateAndDishesSize(menu.getId(), id, nextLunchDate), id);
    }
}
