package com.github.friendlylunch.web.restaurant;

import com.github.friendlylunch.model.Restaurant;
import com.github.friendlylunch.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.friendlylunch.util.Util.nextLunchDate;
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
        assureIdConsistent(restaurant, id);
        restaurantRepository.save(restaurant);
    }

    public void delete(int id) {
        log.info("delete restaurant {}", id);
        checkNotFoundWithId(restaurantRepository.delete(id), id);
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
        return restaurantRepository.getAllCheckedByMenuDateAndDishesSizeWithMenus(nextLunchDate);
    }

    public List<Restaurant> getAllChecked() {
        log.info("getAllChecked restaurants");
        return restaurantRepository.getAllCheckedByMenuDateAndDishesSize(nextLunchDate);
    }

    public Restaurant getChecked(int id) {
        log.info("getChecked restaurant {}", id);
        return checkNotFoundWithId(restaurantRepository.getCheckedByMenuDateAndDishesSize(id, nextLunchDate), id);
    }
}
