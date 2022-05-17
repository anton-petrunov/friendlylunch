package ru.mygraduation.friendlylunch.service;

import org.springframework.stereotype.Service;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.repository.RestaurantRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.mygraduation.friendlylunch.util.ValidationUtil.assureIdConsistent;
import static ru.mygraduation.friendlylunch.util.ValidationUtil.checkNew;

@Service
public class RestaurantAndDishService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantAndDishService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.getAll();
    }

    public Restaurant getRestaurant(int restaurantId) {
        return restaurantRepository.get(restaurantId);
    }

    public void deleteRestaurant(int restaurantId) {
        restaurantRepository.delete(restaurantId);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    public void updateRestaurant(Restaurant restaurant, int restaurantId) {
        assureIdConsistent(restaurant, restaurantId);
        restaurantRepository.save(restaurant);
    }

    public String getDishes(int restaurantId) {
        return getRestaurant(restaurantId).getDishes();
    }

    public void deleteDishes(int restaurantId) {
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        restaurant.setDishes(null);
        restaurantRepository.save(restaurant);
    }

    public void updateDishes(String dishes, int restaurantId) {
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        restaurant.setDishes(dishes);
        restaurant.setDishesUpdateDateTime(LocalDateTime.now());
        restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return restaurantRepository.getBetween(startDateTime, endDateTime);
    }
}
