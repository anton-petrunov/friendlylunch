package ru.mygraduation.friendlylunch.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.repository.RestaurantRepository;
import ru.mygraduation.friendlylunch.repository.UserRepository;

import java.util.List;

import static ru.mygraduation.friendlylunch.util.ValidationUtil.assureIdConsistent;
import static ru.mygraduation.friendlylunch.util.ValidationUtil.checkNew;

public class AdminController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;

    public AdminController(UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants() {
        log.info("getAll restaurants");
        return restaurantRepository.getAll();
    }

    public Restaurant getRestaurant(int restaurantId) {
        log.info("getRestaurant {}", restaurantId);
        return restaurantRepository.get(restaurantId);
    }

    public void deleteRestaurant(int restaurantId) {
        log.info("deleteRestaurant {}", restaurantId);
        restaurantRepository.delete(restaurantId);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    public void updateRestaurant(Restaurant restaurant, int restaurantId) {
        log.info("updateRestaurant {} with id={}", restaurant, restaurantId);
        assureIdConsistent(restaurant, restaurantId);
        restaurantRepository.save(restaurant);
    }

    public String getDishes(int restaurantId) {
        log.info("getDishes of restaurant {}", restaurantId);
        return getRestaurant(restaurantId).getDishes();
    }

    public void deleteDishes(int restaurantId) {
        log.info("delete dishes field of restaurant {}", restaurantId);
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        restaurant.setDishes("");
        restaurantRepository.save(restaurant);
    }

    public void updateDishes(String dishes, int restaurantId) {
        log.info("update dishes field of restaurant {}", restaurantId);
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        restaurant.setDishes(dishes);
        restaurantRepository.save(restaurant);
    }

    public List<User> getAllUsers() {
        log.info("getAll users");
        return userRepository.getAll();
    }

    public User getUser(int userId) {
        log.info("get user {}", userId);
        return userRepository.get(userId);
    }

    public void deleteUser(int userId) {
        log.info("delete user {}", userId);
        userRepository.delete(userId);
    }

    public User createUser(User user) {
        log.info("create {}", user);
        checkNew(user);
        return userRepository.save(user);
    }

    public void updateUser(User user, int userId) {
        log.info("update {} with id={}", user, userId);
        assureIdConsistent(user, userId);
        userRepository.save(user);
    }
}
