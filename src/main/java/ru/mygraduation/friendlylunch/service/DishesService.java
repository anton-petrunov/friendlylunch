package ru.mygraduation.friendlylunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.repository.RestaurantRepository;

import java.time.LocalDateTime;

@Service
public class DishesService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public String get(int restaurantId) {
        return restaurantRepository.get(restaurantId).getDishes();
    }

    public void delete(int restaurantId) {
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        restaurant.setDishes(null);
        restaurantRepository.save(restaurant);
    }

    public void update(String dishes, int restaurantId) {
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        restaurant.setDishes(dishes);
        restaurant.setDishesUpdateDateTime(LocalDateTime.now());
        restaurantRepository.save(restaurant);
    }
}
