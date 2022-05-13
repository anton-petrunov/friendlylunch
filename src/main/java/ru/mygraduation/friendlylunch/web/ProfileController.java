package ru.mygraduation.friendlylunch.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mygraduation.friendlylunch.model.Restaurant;
import ru.mygraduation.friendlylunch.model.User;
import ru.mygraduation.friendlylunch.repository.RestaurantRepository;
import ru.mygraduation.friendlylunch.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.mygraduation.friendlylunch.Util.*;

public class ProfileController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;

    public ProfileController(UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getRestaurantsWithMenuChecked() {
        log.info("getAll restaurants with menu checked");
        return restaurantRepository.getBetween(previousLunchDateTime(), nextLunchDateTime()).stream()
                .filter(r -> r.getId() != null)
                .collect(Collectors.toList());
    }

    public String getRestaurantMenuChecked(int id) {
        log.info("get dishes of checked restaurant {}", id);
        Restaurant restaurant = restaurantRepository.get(id);
        return checkMenu(restaurant) ? restaurant.getDishes() : null;
    }

    public void vote(int restaurantId, int userId) {
        log.info("vote of user {} for restaurant {}", userId, restaurantId);
        if (checkMenu(restaurantRepository.get(restaurantId))) {
            User user = userRepository.get(userId);
            if (checkVotingAvailability(user) || checkRevoteAvailability(user)) {
                user.setVotedFor(restaurantId);
                user.setVotingDateTime(dateTimeNow);
                userRepository.save(user);
            }
        }
    }

    public String getUserVote(int id) {
        User user = userRepository.get(id);
        log.info("get vote of user {}", id);
        return checkVote(user) ? user.getVotedFor() + "\n" + user.getVotingDateTime().toString() : "Voting is out of day";
    }
}
