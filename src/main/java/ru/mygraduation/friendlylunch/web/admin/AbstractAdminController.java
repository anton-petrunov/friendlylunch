package ru.mygraduation.friendlylunch.web.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.service.DishesService;
import ru.mygraduation.friendlylunch.service.RestaurantService;
import ru.mygraduation.friendlylunch.service.UserService;

import java.util.List;

public abstract class AbstractAdminController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishesService dishesService;

    @Autowired
    private UserService userService;

    public List<Restaurant> getAllRestaurants() {
        log.info("getAll restaurants");
        return restaurantService.getAll();
    }

    public Restaurant getRestaurant(int restaurantId) {
        log.info("getRestaurant {}", restaurantId);
        return restaurantService.get(restaurantId);
    }

    public void deleteRestaurant(int restaurantId) {
        log.info("deleteRestaurant {}", restaurantId);
        restaurantService.delete(restaurantId);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        log.info("create {}", restaurant);
        return restaurantService.createRestaurant(restaurant);
    }

    public void updateRestaurant(Restaurant restaurant, int restaurantId) {
        log.info("updateRestaurant {} with id={}", restaurant, restaurantId);
        restaurantService.update(restaurant, restaurantId);
    }

    public String getDishes(int restaurantId) {
        log.info("getDishes of restaurant {}", restaurantId);
        return dishesService.get(restaurantId);
    }

    public void deleteDishes(int restaurantId) {
        log.info("delete dishes field of restaurant {}", restaurantId);
        dishesService.delete(restaurantId);
    }

    public void updateDishes(String dishes, int restaurantId) {
        log.info("update dishes field of restaurant {}", restaurantId);
        dishesService.update(dishes, restaurantId);
    }

    public List<User> getAllUsers() {
        log.info("getAll users");
        return userService.getAll();
    }

    public User getUser(int userId) {
        log.info("get user {}", userId);
        return userService.get(userId);
    }

    public void deleteUser(int userId) {
        log.info("delete user {}", userId);
        userService.delete(userId);
    }

    public User createUser(User user) {
        log.info("create {}", user);
        return userService.create(user);
    }

    public void updateUser(User user, int userId) {
        log.info("update {} with id={}", user, userId);
        userService.updateWithPasswordEncoding(user, userId);
    }
}
