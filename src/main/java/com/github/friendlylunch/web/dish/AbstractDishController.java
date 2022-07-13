package com.github.friendlylunch.web.dish;

import com.github.friendlylunch.model.Dish;
import com.github.friendlylunch.repository.DishRepository;
import com.github.friendlylunch.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.friendlylunch.util.Util.nextLunchDate;
import static com.github.friendlylunch.util.ValidationUtil.*;

public abstract class AbstractDishController {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private MenuRepository menuRepository;

    public Dish create(Dish dish, int restaurantId, int menuId) {
        checkNew(dish);
        dish.setMenu(menuRepository.get(restaurantId, menuId));
        return dishRepository.save(dish);
    }

    public void update(Dish dish, int restaurantId, int menuId, int id) {
        assureIdConsistent(dish, id);
        Dish dishFromPath = dishRepository.get(restaurantId, menuId, id);
        dish.setMenu(dishFromPath.getMenu());
        dish.getMenu().setRestaurant(dishFromPath.getMenu().getRestaurant());
        dishRepository.save(dish);
    }

    public void delete(int menuId, int id) {
        checkNotFoundWithId(dishRepository.delete(menuId, id), id);
    }

    public Dish get(int restaurantId, int menuId, int id) {
        return checkNotFoundWithId(dishRepository.get(restaurantId, menuId, id), id);
    }

    public List<Dish> getAll(int restaurantId, int menuId) {
        return dishRepository.getAll(restaurantId, menuId);
    }

    public List<Dish> getAllChecked(int restaurantId, int menuId) {
        return dishRepository.getAllCheckedByMenuDateAndDishesSize(restaurantId, menuId, nextLunchDate);
    }

    public Dish getChecked(int restaurantId, int menuId, int id) {
        return checkNotFoundWithId(
                dishRepository.getCheckedByMenuDateAndDishesSize(restaurantId, menuId, id, nextLunchDate), id);
    }
}
