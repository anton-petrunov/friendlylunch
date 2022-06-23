package com.github.friendlylunch.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.friendlylunch.service.DishesService;

public class DishController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DishesService dishesService;

    public String get(int restaurantId) {
        log.info("get dishes of restaurant {}", restaurantId);
        return dishesService.get(restaurantId);
    }

    public void delete(int restaurantId) {
        log.info("delete dishes of restaurant {}", restaurantId);
        dishesService.delete(restaurantId);
    }

    public void update(String dishes, int restaurantId) {
        log.info("update dishes of restaurant {}", restaurantId);
        dishesService.update(dishes, restaurantId);
    }
}
