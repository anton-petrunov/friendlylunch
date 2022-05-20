package ru.mygraduation.friendlylunch.web.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.service.RestaurantService;
import ru.mygraduation.friendlylunch.service.UserService;
import ru.mygraduation.friendlylunch.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.mygraduation.friendlylunch.util.Util.*;

public abstract class AbstractProfileController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    public List<Restaurant> getRestaurantsWithMenuChecked() {
        log.info("getAll restaurants with menu checked");
        return restaurantService.getBetween(previousLunchDateTime(), nextLunchDateTime()).stream()
                .filter(r -> r.getDishes() != null)
                .collect(Collectors.toList());
    }

    public String getRestaurantMenuChecked(int id) {
        log.info("get dishes of checked restaurant {}", id);
        Restaurant restaurant = restaurantService.get(id);
        if (checkMenu(restaurant)) {
            return restaurant.getDishes();
        } else {
            throw new NotFoundException("Restaurant " + id + " is not available for voting");
        }
    }

    public void vote(int restaurantId, int userId) {
        log.info("vote of user {} for restaurant {}", userId, restaurantId);
        if (checkMenu(restaurantService.get(restaurantId))) {
            User user = userService.get(userId);
            if (checkVotingAvailability(user) || checkRevoteAvailability(user)) {
                user.setVotedFor(restaurantId);
                user.setVotingDateTime(LocalDateTime.now());
                userService.updateWithoutPasswordEncoding(user, userId);
            }
        }
    }

    public String getUserVote(int id) {
        log.info("get vote of user {}", id);
        User user = userService.get(id);
        return user.getVotedFor() + "\n" + user.getVotingDateTime().toString();
    }

    public User create(User user) {
        log.info("create {}", user);
        return userService.create(user);
    }
}
