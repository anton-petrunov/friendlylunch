package ru.mygraduation.friendlylunch.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.service.RestaurantService;

import java.util.List;

public class RestaurantController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    RestaurantService restaurantService;

    public List<Restaurant> getAll() {
        log.info("getAll restaurants");
        return restaurantService.getAll();
    }

    public Restaurant get(int id) {
        log.info("get restaurant {}", id);
        return restaurantService.get(id);
    }

    public void delete(int id) {
        log.info("delete restaurant {}", id);
        restaurantService.delete(id);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        return restaurantService.createRestaurant(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} with id={}", restaurant, id);
        restaurantService.update(restaurant, id);
    }
}
