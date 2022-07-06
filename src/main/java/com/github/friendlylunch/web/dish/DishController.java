package com.github.friendlylunch.web.dish;

import com.github.friendlylunch.model.Dish;
import com.github.friendlylunch.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.friendlylunch.util.ValidationUtil.assureIdConsistent;
import static com.github.friendlylunch.util.ValidationUtil.checkNew;

public class DishController {

    @Autowired
    private DishRepository dishRepository;

    public Dish create(Dish dish) {
        checkNew(dish);
        return dishRepository.save(dish);
    }

    public void update(Dish dish, int id) {
        assureIdConsistent(dish, id);
        dishRepository.save(dish);
    }

    public void delete(int menuId, int id) {
        dishRepository.delete(menuId, id);
    }

    public Dish get(int restaurantId, int menuId, int id) {
        return dishRepository.get(restaurantId, menuId, id);
    }

    public List<Dish> getAll(int restaurantId, int menuId) {
        return dishRepository.getAll(restaurantId, menuId);
    }
}
