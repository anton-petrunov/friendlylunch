package ru.mygraduation.friendlylunch.web.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.service.RestaurantAndDishService;
import ru.mygraduation.friendlylunch.service.UserService;
import ru.mygraduation.friendlylunch.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.mygraduation.friendlylunch.util.Util.*;
import static ru.mygraduation.friendlylunch.util.ValidationUtil.checkNew;

public abstract class AbstractProfileController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantAndDishService restaurantAndDishService;

    @Autowired
    private UserService userService;

    public List<Restaurant> getRestaurantsWithMenuChecked() {
        log.info("getAll restaurants with menu checked");
        return restaurantAndDishService.getBetween(previousLunchDateTime(), nextLunchDateTime()).stream()
                .filter(r -> r.getDishes() != null)
                .collect(Collectors.toList());
    }

    public String getRestaurantMenuChecked(int id) {
        log.info("get dishes of checked restaurant {}", id);
        Restaurant restaurant = restaurantAndDishService.getRestaurant(id);
        if (checkMenu(restaurant)) {
            return restaurant.getDishes();
        } else {
            throw new NotFoundException("Restaurant " + id + " is not available for voting");
        }
    }

    public void vote(int restaurantId, int userId) {
        log.info("vote of user {} for restaurant {}", userId, restaurantId);
        if (checkMenu(restaurantAndDishService.getRestaurant(restaurantId))) {
            User user = userService.getUser(userId);
            String passwordWithoutEncoding = user.getPassword();
            if (checkVotingAvailability(user) || checkRevoteAvailability(user)) {
                user.setVotedFor(restaurantId);
                user.setVotingDateTime(LocalDateTime.now());
                userService.updateWithoutPasswordEncoding(user, userId);
            }
        }
    }

    public String getUserVote(int id) {
        log.info("get vote of user {}", id);
        User user = userService.getUser(id);
        return user.getVotedFor() + "\n" + user.getVotingDateTime().toString();
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return userService.createUser(user);
    }
}
