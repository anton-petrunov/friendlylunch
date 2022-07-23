package com.github.friendlylunch.util;

import com.github.friendlylunch.model.Dish;
import com.github.friendlylunch.model.Menu;
import com.github.friendlylunch.model.Restaurant;
import com.github.friendlylunch.model.User;
import com.github.friendlylunch.util.exception.IllegalRequestDataException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Util {

    public static final LocalTime LUNCH_TIME = LocalTime.of(14, 0);

    private static final String RESTAURANTS = "restaurants";
    private static final String RESTAURANT = "Restaurant ";
    private static final String NOT_FOUND = "Not found ";
    private static final String AVAILABLE_FOR_VOTING = " available for voting";
    private static final String IS_NOT = " is not";

    private static final String MENUS = "menus";
    private static final String MENU = "Menu ";
    private static final String OF = " of ";
    private static final String DISHES = "dishes";
    private static final String DISH = "Dish ";

    public static LocalDateTime nextLunchDateTime() {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        if (dateTimeNow.toLocalTime().isBefore(LUNCH_TIME)) {
            return LocalDateTime.of(dateTimeNow.toLocalDate(), LUNCH_TIME);
        } else {
            return LocalDateTime.of(dateTimeNow.toLocalDate().plus(1, ChronoUnit.DAYS), LUNCH_TIME);
        }
    }

    public static LocalDate nextLunchDate = nextLunchDateTime().toLocalDate();

    public static LocalDateTime previousLunchDateTime() {
        return nextLunchDateTime().minus(1, ChronoUnit.DAYS);
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    public static void checkRestaurantsListSize(List<Restaurant> restaurants) {
        if (restaurants.size() == 0) {
            throw new IllegalRequestDataException(NOT_FOUND + RESTAURANTS + AVAILABLE_FOR_VOTING);
        }
    }

    public static void checkRestaurantIsNull(Restaurant restaurant, int id) {
        if (restaurant == null) {
            throw new IllegalRequestDataException(RESTAURANT + id + IS_NOT + AVAILABLE_FOR_VOTING);
        }
    }

    public static void checkMenusListSize(List<Menu> menus, int restaurantId) {
        if (menus.size() == 0) {
            throw new IllegalRequestDataException(NOT_FOUND + MENUS + OF + RESTAURANT.toLowerCase() +
                    restaurantId + AVAILABLE_FOR_VOTING);
        }
    }

    public static void checkMenuIsNull(Menu menu, int restaurantId, int id) {
        if (menu == null) {
            throw new IllegalRequestDataException(MENU + id + OF + RESTAURANT.toLowerCase() +
                    restaurantId + IS_NOT + AVAILABLE_FOR_VOTING);
        }
    }

    public static void checkDishesListSize(List<Dish> dishes, int restaurantId, int menuId) {
        if (dishes.size() == 0) {
            throw new IllegalRequestDataException(NOT_FOUND + DISHES + OF + RESTAURANT.toLowerCase() +
                    restaurantId + OF + MENU.toLowerCase() + menuId + AVAILABLE_FOR_VOTING);
        }
    }

    public static void checkDishIsNull(Dish dish, int restaurantId, int menuId, int id) {
        if (dish == null) {
            throw new IllegalRequestDataException(DISH + id + OF + MENU.toLowerCase() + menuId +
                    OF + RESTAURANT.toLowerCase() + restaurantId + IS_NOT + AVAILABLE_FOR_VOTING);
        }
    }
}
