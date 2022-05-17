package ru.mygraduation.friendlylunch.web.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.service.RestaurantAndDishService;
import ru.mygraduation.friendlylunch.service.UserService;

import java.util.List;

public abstract class AbstractAdminController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantAndDishService restaurantAndDishService;

    @Autowired
    private UserService userService;

    public List<Restaurant> getAllRestaurants() {
        log.info("getAll restaurants");
        return restaurantAndDishService.getAllRestaurants();
    }

    public Restaurant getRestaurant(int restaurantId) {
        log.info("getRestaurant {}", restaurantId);
        return restaurantAndDishService.getRestaurant(restaurantId);
    }

    public void deleteRestaurant(int restaurantId) {
        log.info("deleteRestaurant {}", restaurantId);
        restaurantAndDishService.deleteRestaurant(restaurantId);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        log.info("create {}", restaurant);
        return restaurantAndDishService.createRestaurant(restaurant);
    }

    public void updateRestaurant(Restaurant restaurant, int restaurantId) {
        log.info("updateRestaurant {} with id={}", restaurant, restaurantId);
        restaurantAndDishService.updateRestaurant(restaurant, restaurantId);
    }

    public String getDishes(int restaurantId) {
        log.info("getDishes of restaurant {}", restaurantId);
        return getRestaurant(restaurantId).getDishes();
    }

    public void deleteDishes(int restaurantId) {
        log.info("delete dishes field of restaurant {}", restaurantId);
        restaurantAndDishService.deleteDishes(restaurantId);
    }

    public void updateDishes(String dishes, int restaurantId) {
        log.info("update dishes field of restaurant {}", restaurantId);
        restaurantAndDishService.updateDishes(dishes, restaurantId);
    }

    public List<User> getAllUsers() {
        log.info("getAll users");
        return userService.getAllUsers();
    }

    public User getUser(int userId) {
        log.info("get user {}", userId);
        return userService.getUser(userId);
    }

    public void deleteUser(int userId) {
        log.info("delete user {}", userId);
        userService.deleteUser(userId);
    }

    public User createUser(User user) {
        log.info("create {}", user);
        return userService.createUser(user);
    }

    public void updateUser(User user, int userId) {
        log.info("update {} with id={}", user, userId);
        userService.updateUser(user, userId);
    }
}
