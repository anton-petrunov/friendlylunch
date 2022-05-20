package ru.mygraduation.friendlylunch.service;

import org.springframework.stereotype.Service;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.repository.RestaurantRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.mygraduation.friendlylunch.util.ValidationUtil.assureIdConsistent;
import static ru.mygraduation.friendlylunch.util.ValidationUtil.checkNew;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    public Restaurant get(int restaurantId) {
        return restaurantRepository.get(restaurantId);
    }

    public void delete(int restaurantId) {
        restaurantRepository.delete(restaurantId);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    public void update(Restaurant restaurant, int restaurantId) {
        assureIdConsistent(restaurant, restaurantId);
        restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return restaurantRepository.getBetween(startDateTime, endDateTime);
    }
}
