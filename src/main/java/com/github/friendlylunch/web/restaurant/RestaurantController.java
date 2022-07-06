package com.github.friendlylunch.web.restaurant;

import com.github.friendlylunch.model.Restaurant;
import com.github.friendlylunch.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.github.friendlylunch.util.ValidationUtil.assureIdConsistent;
import static com.github.friendlylunch.util.ValidationUtil.checkNew;

public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        assureIdConsistent(restaurant, id);
        restaurantRepository.save(restaurant);
    }

    public void delete(int id) {
        restaurantRepository.delete(id);
    }

    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant getWithMenus(int id) {
        return restaurantRepository.getWithMenus(id);
    }
}
