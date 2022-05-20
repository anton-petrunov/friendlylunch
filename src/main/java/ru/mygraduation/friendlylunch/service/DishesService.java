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

    public String get(int id) {
        return restaurantRepository.get(id).getDishes();
    }

    public void delete(int id) {
        Restaurant restaurant = restaurantRepository.get(id);
        restaurant.setDishes(null);
        restaurantRepository.save(restaurant);
    }

    public void update(String dishes, int id) {
        Restaurant restaurant = restaurantRepository.get(id);
        restaurant.setDishes(dishes);
        restaurant.setDishesUpdateDateTime(LocalDateTime.now());
        restaurantRepository.save(restaurant);
    }
}