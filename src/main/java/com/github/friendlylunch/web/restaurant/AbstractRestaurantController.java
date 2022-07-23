package com.github.friendlylunch.web.restaurant;

import com.github.friendlylunch.model.Restaurant;
import com.github.friendlylunch.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

import static com.github.friendlylunch.util.Util.*;
import static com.github.friendlylunch.util.ValidationUtil.*;

public abstract class AbstractRestaurantController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} with id {}", restaurant, id);
        Assert.notNull(restaurant, "restaurant must not be null");
        Restaurant updated = restaurantRepository.findById(id).orElse(null);
        checkNotFoundWithId(updated, id);
        assureIdConsistent(restaurant, id);
        restaurantRepository.save(restaurant);
    }

    public void delete(int id) {
        log.info("delete restaurant {}", id);
        checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
    }

    public Restaurant get(int id) {
        log.info("get restaurant {}", id);
        return checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }

    public List<Restaurant> getAll() {
        log.info("getAll restaurants");
        return restaurantRepository.findAll();
    }

    public Restaurant getWithMenus(int id) {
        log.info("get restaurant {} with menus", id);
        return checkNotFoundWithId(restaurantRepository.getWithMenus(id), id);
    }

    public List<Restaurant> getAllCheckedWithMenus() {
        log.info("getAllChecked restaurants with menus");
        List<Restaurant> restaurants = restaurantRepository.getAllCheckedByMenuDateAndDishesSizeWithMenus(nextLunchDate);
        checkRestaurantsListSize(restaurants);
        return restaurants;
    }

    public List<Restaurant> getAllChecked() {
        log.info("getAllChecked restaurants");
        List<Restaurant> restaurants = restaurantRepository.getAllCheckedByMenuDateAndDishesSize(nextLunchDate);
        checkRestaurantsListSize(restaurants);
        return restaurants;
    }

    public Restaurant getChecked(int id) {
        log.info("getChecked restaurant {}", id);
        checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
        Restaurant restaurant = restaurantRepository.getCheckedByMenuDateAndDishesSize(id, nextLunchDate);
        checkRestaurantIsNull(restaurant, id);
        return restaurant;
    }
}
