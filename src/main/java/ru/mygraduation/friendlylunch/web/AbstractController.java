package ru.mygraduation.friendlylunch.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.service.DishesService;
import ru.mygraduation.friendlylunch.service.RestaurantService;
import ru.mygraduation.friendlylunch.service.UserService;

public class AbstractController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RestaurantService restaurantService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected DishesService dishesService;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        log.info("create {}", user);
        return userService.create(user);
    }
}
