package ru.mygraduation.friendlylunch.web.profile;

import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.util.exception.NotFoundException;
import ru.mygraduation.friendlylunch.web.AbstractController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.mygraduation.friendlylunch.util.Util.*;

public abstract class AbstractProfileController extends AbstractController {

    public List<Restaurant> getRestaurantsWithCheckedDishes() {
        log.info("getAll restaurants with checked menu");
        return restaurantService.getBetween(previousLunchDateTime(), nextLunchDateTime()).stream()
                .filter(r -> r.getDishes() != null)
                .collect(Collectors.toList());
    }

    public String getDishesOfCheckedRestaurant(int id) {
        log.info("get dishes of checked restaurant {}", id);
        Restaurant restaurant = restaurantService.get(id);
        if (checkDishes(restaurant)) {
            return restaurant.getDishes();
        } else {
            throw new NotFoundException("Restaurant " + id + " is not available for voting");
        }
    }

    public void vote(int restaurantId, int userId) {
        log.info("vote of user {} for restaurant {}", userId, restaurantId);
        if (checkDishes(restaurantService.get(restaurantId))) {
            User user = userService.get(userId);
            if (checkVotingAvailability(user) || checkRevoteAvailability(user)) {
                user.setVotedFor(restaurantId);
                user.setVotingDateTime(LocalDateTime.now());
                userService.updateWithoutPasswordEncoding(user, userId);
            }
        }
    }

    public Map<String, String> getProfileVote(int id) {
        log.info("get vote of user {}", id);
        User user = userService.get(id);
        return Map.of("VotedFor", user.getVotedFor().toString(),
                "VotingDateTime", user.getVotingDateTime().toString());
    }
}
