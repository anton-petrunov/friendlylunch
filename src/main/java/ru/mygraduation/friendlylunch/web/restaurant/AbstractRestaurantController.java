package ru.mygraduation.friendlylunch.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.service.RestaurantService;
import ru.mygraduation.friendlylunch.util.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static ru.mygraduation.friendlylunch.util.Util.*;
import static ru.mygraduation.friendlylunch.util.ValidationUtil.assureIdConsistent;
import static ru.mygraduation.friendlylunch.util.ValidationUtil.checkNew;

public abstract class AbstractRestaurantController {

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
        checkNew(restaurant);
        return restaurantService.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    public List<Restaurant> getRestaurantsWithCheckedDishes() {
        log.info("getAll restaurants with checked menu");
        return restaurantService.getBetween(previousLunchDateTime(), nextLunchDateTime()).stream()
                .filter(r -> r.getDishes() != null)
                .collect(Collectors.toList());
    }

    public String getDishesOfCheckedRestaurant(int id) {
        log.info("get dishes of checked restaurant {}", id);
        Restaurant restaurant = restaurantService.get(id);
        if (checkDishes(restaurant)) {
            return restaurant.getDishes();
        } else {
            throw new NotFoundException("Restaurant " + id + " is not available for voting");
        }
    }
}