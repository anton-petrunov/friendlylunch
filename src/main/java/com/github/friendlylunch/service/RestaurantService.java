package com.github.friendlylunch.service;

import com.github.friendlylunch.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import com.github.friendlylunch.model.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository crudRestaurantRepository) {
        this.restaurantRepository = crudRestaurantRepository;
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public void delete(int id) {
        restaurantRepository.delete(id);
    }

    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void update(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return restaurantRepository.getBetween(startDateTime, endDateTime);
    }
}
